package com.example.chatapp.ui.home.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.display.DisplayManager
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.webkit.MimeTypeMap
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import com.example.chatapp.R
import com.example.chatapp.ui.home.HomeActivity
import com.example.chatapp.ui.home.KEY_EVENT_ACTION
import com.example.chatapp.ui.home.KEY_EVENT_EXTRA
import com.example.chatapp.ui.home.*
import com.example.chatapp.utils.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraFragment : Fragment() {

    private lateinit var container: ViewGroup
    private lateinit var viewFinder: PreviewView
    private lateinit var outputDirectory: File
    private lateinit var broadcastManager: LocalBroadcastManager
    private lateinit var cameraExecutor: ExecutorService

    private var zoom = 0.0f
    private var dist = 0.0f
    private var displayId: Int = -1
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var flashMode = ImageCapture.FLASH_MODE_OFF
    private var imageAnalyzer: ImageAnalysis? = null
    private var camera: Camera? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private var flashDrawable = R.drawable.ic_flash_off_black_24dp

    private val customLifecycle = CustomLifecycle()
    private val relativeDensity by lazy {
        (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT).toInt() //divide by px or multiply by dp
    }
    private val displayManager by lazy {
        requireContext().getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    }
    private val volumeDownReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getIntExtra(KEY_EVENT_EXTRA, KeyEvent.KEYCODE_UNKNOWN)) {
                // When the volume down button is pressed, simulate a shutter button click
                KeyEvent.KEYCODE_VOLUME_DOWN -> {
                    val shutter = container
                        .findViewById<ImageButton>(R.id.camera_capture_button)
                    shutter.simulateClick()
                }
            }
        }
    }
    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) = Unit
        override fun onDisplayRemoved(displayId: Int) = Unit
        override fun onDisplayChanged(displayId: Int) = view?.let { view ->
            if (displayId == this@CameraFragment.displayId) {
                Log.d(TAG, "Rotation changed: ${view.display.rotation}")
                imageCapture?.targetRotation = view.display.rotation
                imageAnalyzer?.targetRotation = view.display.rotation
            }
        } ?: Unit
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.camera_fragment, container, false)

    override fun onResume() {
        super.onResume()
        if (!PermissionsFragment.hasPermissions(requireContext())) {
            val dialog = PermissionsDialogFragment()
            dialog.show(childFragmentManager, "PermissionsDialogFragment")
        }
        val view = requireView()
        container = view as ViewGroup
        viewFinder = container.findViewById(R.id.viewFinder)
        // Initialize our background executor
        cameraExecutor = Executors.newSingleThreadExecutor()

        broadcastManager = LocalBroadcastManager.getInstance(view.context)
        val filter = IntentFilter().apply { addAction(KEY_EVENT_ACTION) }
        broadcastManager.registerReceiver(volumeDownReceiver, filter)

        displayManager.registerDisplayListener(displayListener, null)

        // Determine the output directory
        outputDirectory = HomeActivity.getOutputDirectory(requireContext())

        // Wait for the views to be properly laid out
        viewFinder.post {
            displayId = viewFinder.display.displayId // Keep track of display in which view attached
            updateCameraUi() // Build UI controls
            setUpCamera() // Set up the camera and its use cases (contains bindCameraUseCase)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateCameraUi()
        updateCameraSwitchButton(container, cameraProvider)
    }

    private var _hasLoadedOnce = false
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (this.isVisible) {
            if (!_hasLoadedOnce) {
                _hasLoadedOnce = true
            }
        }
    }

    private fun setUpCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({

            cameraProvider = cameraProviderFuture.get()
            lensFacing = lensFacing(cameraProvider!!)
            updateCameraSwitchButton(container, cameraProvider)
            if (viewFinder.display != null)
                bindCameraUseCases() // Build and bind the camera use cases
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun bindCameraUseCases() {
        // Get screen metrics used to setup camera for full screen resolution
        val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
        Log.d(TAG, "Screen metrics: ${metrics.widthPixels} x ${metrics.heightPixels}")
        val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)
        Log.d(TAG, "Preview aspect ratio: $screenAspectRatio")

        val rotation = viewFinder.display.rotation

        val cameraProvider = cameraProvider
            ?: throw IllegalStateException("Camera initialization failed.")

        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

        preview = previewBuilder(screenAspectRatio, rotation)
        imageCapture = imageCaptureBuilder(screenAspectRatio, rotation, flashMode)
        imageAnalyzer = imageAnalysisBuilder(screenAspectRatio, rotation, cameraExecutor)

/*
        val videoCaptureConfig = VideoCaptureConfig.Builder().apply {
            setLensFacing(lensFacing)
            setTargetAspectRatio(screenAspectRatio)
            setTargetRotation(viewFinder.display.rotation)

        }.build()

        videoCapture = VideoCapture(videoCaptureConfig.useCaseConfig)
*/
        cameraProvider.unbindAll()

        try {
            customLifecycle.startAndResume()
            camera = cameraProvider.bindToLifecycle(
                customLifecycle,
                cameraSelector,
                preview,
                imageCapture,
                imageAnalyzer
            )
            preview?.setSurfaceProvider(viewFinder.surfaceProvider)
        } catch (exc: Exception) {
            Log.e(TAG, "Use case binding failed", exc)
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun updateCameraUi() {

        // Remove previous UI if any
        container.findViewById<ViewGroup>(R.id.camera_ui_container)?.let {
            container.removeView(it)
        }
        // Inflate a new view containing all UI for controlling the camera
        val controls = View.inflate(requireContext(), R.layout.camera_ui_container, container)

        // ****************************************************************************************
        val view = requireView()
        view.setOnTouchListener { _, event ->
            if (event!!.pointerCount > 1) {
                view.parent.requestDisallowInterceptTouchEvent(true)
                Log.e("this", "setOnTouchListener ${getFingerSpacing(event)}")
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_POINTER_DOWN -> {
                        dist = getFingerSpacing(event)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val maxZoom = 1f
                        val newDist: Float = getFingerSpacing(event)
                        if (newDist > dist) {
                            if (zoom < maxZoom) {
                                zoom += 0.05f ////zoom in
                            }
                        } else if (newDist < dist && zoom > 0) {
                            zoom -= 0.05f //zoom out
                        }
                        dist = newDist
                        camera?.cameraControl?.setLinearZoom(zoom)
                    }
                }
            } else if (event.pointerCount == 1) {
                Log.e("Event.action", "${event.action}")
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {

                        val factory: MeteringPointFactory =
                            view.findViewById<PreviewView>(R.id.viewFinder).meteringPointFactory
                        val point = factory.createPoint(event.x, event.y)
                        Log.e("event.x && event.y", "${event.x} ${event.y}")
                        val action = FocusMeteringAction.Builder(point).apply {
                            //focus only when the user tap the preview
                            //disableAutoCancel()
                        }.build()

                        val focusListenableFuture = camera?.cameraControl?.startFocusAndMetering(action)
                        focusListenableFuture?.addListener({
                            try {
                            val result = focusListenableFuture.get()
                            val isSuccessful = result.isFocusSuccessful
                                if(isSuccessful)
                            Log.e("isSuccessful","$isSuccessful ${focusListenableFuture.isDone}")
                            } catch (e:java.lang.Exception) {
                                e.printStackTrace()
                            }
                        }, ContextCompat.getMainExecutor(context))
                        //Log.e("focusDone", "${focusDone}")
                        return@setOnTouchListener true
                    }
                }
            }
            true
        }

        if (view.findViewById<FrameLayout>(R.id.bottom_sheet) != null) {
            val bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById<FrameLayout>(R.id.bottom_sheet))
            bottomSheetBehavior.peekHeight =
                194 * relativeDensity //convertDpToPixel(194, requireContext()).toInt()
            bottomSheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {}

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (slideOffset == 1F) {
                        // Todo bottomSheet slide up
                    } else if (slideOffset == 0F) {
                        // Todo bottomSheet slide down
                    }
                }

            })
        }
        //*****************************************************************************************
        // In the background, load latest photo taken (if any) for gallery thumbnail
        lifecycleScope.launch(Dispatchers.IO) {
            outputDirectory.listFiles { file ->
                EXTENSION_WHITELIST.contains(file.extension.toUpperCase(Locale.ROOT))
            }?.maxOrNull()?.let {
                //setGalleryThumbnail(Uri.fromFile(it))
            }
        }

        // ************************************************************************************** //

        controls.findViewById<ImageButton>(R.id.camera_capture_button).setOnClickListener {


            //Todo pause previewView

            // Photo Showing Fragment
            // Get a stable reference of the modifiable image capture use case
            imageCapture?.let { imageCapture ->
                // Create output file to hold the image
                val photoFile = createFile(outputDirectory, FILENAME, PHOTO_EXTENSION)

                // Setup image capture metadata
                val metadata = ImageCapture.Metadata().apply {
                    // Mirror image when using the front camera
                    isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
                }

                // Create output options object which contains file + metadata
                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
                    .setMetadata(metadata)
                    .build()

                // Setup image capture listener which is triggered after photo has been taken
                imageCapture.takePicture(
                    outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
                        override fun onError(exc: ImageCaptureException) {
                            Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                        }

                        override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                            val savedUri = output.savedUri ?: Uri.fromFile(photoFile)
                            Log.e(TAG, "Photo capture succeeded: $savedUri")

                            Navigation.findNavController(
                                requireActivity(), R.id.fragment_container
                            )
                                .navigate(
                                    CameraFragmentDirections.actionCameraFragmentToCameraImageResultFragment(
                                        savedUri
                                    )
                                )
                            //savedUri.toString()
                            // If the folder selected is an external media directory, this is
                            // unnecessary but otherwise other apps will not be able to access our
                            // images unless we scan them using [MediaScannerConnection]
                            val mimeType = MimeTypeMap.getSingleton()
                                .getMimeTypeFromExtension(savedUri.toFile().extension)
                            MediaScannerConnection.scanFile(
                                context,
                                arrayOf(savedUri.toFile().absolutePath),
                                arrayOf(mimeType)
                            ) { _, uri ->
                                Log.e(TAG, "\"Photo capture succeeded\",: $uri")
                            }
                        }
                    })

                // Display flash animation to indicate that photo was captured
                container.postDelayed({
                    container.foreground = ColorDrawable(Color.WHITE)
                    container.postDelayed(
                        { container.foreground = null }, ANIMATION_FAST_MILLIS
                    )
                }, ANIMATION_SLOW_MILLIS)
            }
        }
        // ************************************************************************************** //
        controls.findViewById<ImageButton>(R.id.camera_switch_button).let {
            // Disable the button until the camera is set up
            it.isEnabled = false
            it.setOnClickListener {
                lensFacing = if (CameraSelector.LENS_FACING_FRONT == lensFacing) {
                    CameraSelector.LENS_FACING_BACK
                } else {
                    CameraSelector.LENS_FACING_FRONT
                }
                bindCameraUseCases()
            }
        }

        controls.findViewById<ImageButton>(R.id.torchImageButton).setOnClickListener {
            val height = it.height
            it.animate()
                .translationY(height.toFloat())
                .setDuration(100)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        it.translationY = -(height / 2).toFloat()
                        when (flashDrawable) {
                            R.drawable.ic_flash_auto_black_24dp -> {
                                flashDrawable = R.drawable.ic_flash_off_black_24dp
                                (it as ImageButton).setImageResource(flashDrawable)
                                flashMode = ImageCapture.FLASH_MODE_OFF
                            }
                            R.drawable.ic_flash_off_black_24dp -> {
                                flashDrawable = R.drawable.ic_flash_on_black_24dp
                                (it as ImageButton).setImageResource(flashDrawable)
                                flashMode = ImageCapture.FLASH_MODE_ON
                            }
                            else -> {
                                flashDrawable = R.drawable.ic_flash_auto_black_24dp
                                (it as ImageButton).setImageResource(flashDrawable)
                                flashMode = ImageCapture.FLASH_MODE_AUTO
                            }
                        }
                        bindCameraUseCases()
                        it.animate().translationY(0f).setDuration(50).setListener(null).start()
                    }
                })
                .start()
        }
    }

    override fun onPause() {
        super.onPause()
        customLifecycle.destroy()
        cameraExecutor.shutdown()
        broadcastManager.unregisterReceiver(volumeDownReceiver)
        displayManager.unregisterDisplayListener(displayListener)
    }

    companion object {
        private const val TAG = "Chat App"
        private const val FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val PHOTO_EXTENSION = ".jpg"

        /** Helper function used to create a timestamped file */
        private fun createFile(baseFolder: File, format: String, extension: String) =
            File(
                baseFolder, SimpleDateFormat(format, Locale.US)
                    .format(System.currentTimeMillis()) + extension
            )
    }
}