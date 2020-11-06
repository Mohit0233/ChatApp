package com.example.chatapp.utils

import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.chatapp.R
import java.nio.ByteBuffer
import java.util.ArrayDeque
import java.util.concurrent.ExecutorService
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

private const val RATIO_4_3_VALUE = 4.0 / 3.0
private const val RATIO_16_9_VALUE = 16.0 / 9.0

fun getFingerSpacing(event: MotionEvent): Float {
    return try {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        sqrt(x * x + y * y.toDouble()).toFloat()
    } catch (e: Exception) {
        Log.e("exc", "->" + e.message)
        0F
    }
}

/**
 *  [androidx.camera.core.impl.ImageAnalysisConfig] requires enum value of
 *  [androidx.camera.core.AspectRatio]. Currently it has values of 4:3 & 16:9.
 *
 *  Detecting the most suitable ratio for dimensions provided in @params by counting absolute
 *  of preview ratio to one of the provided values.
 *
 *  @param width - preview width
 *  @param height - preview height
 *  @return suitable aspect ratio
 */
fun aspectRatio(width: Int, height: Int): Int {
    val previewRatio = max(width, height).toDouble() / min(width, height)
    if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
        return AspectRatio.RATIO_4_3
    }
    return AspectRatio.RATIO_16_9
}


/** Enabled or disabled a button to switch cameras depending on the available cameras */
fun updateCameraSwitchButton(
    container: ViewGroup,
    cameraProvider: ProcessCameraProvider?
) {
    val switchCamerasButton = container.findViewById<ImageButton>(R.id.camera_switch_button)
    try {
        switchCamerasButton.isEnabled = hasBackCamera(cameraProvider) && hasFrontCamera(
            cameraProvider
        )
    } catch (exception: CameraInfoUnavailableException) {
        switchCamerasButton.isEnabled = false
    }
}


fun lensFacing(cameraProvider: ProcessCameraProvider) = when {
    hasBackCamera(cameraProvider) -> CameraSelector.LENS_FACING_BACK
    hasFrontCamera(cameraProvider) -> CameraSelector.LENS_FACING_FRONT
    else -> throw IllegalStateException("Back and front camera are unavailable")
}


/** Returns true if the device has an available back camera. False otherwise */
fun hasBackCamera(cameraProvider: ProcessCameraProvider?): Boolean {
    return cameraProvider?.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA) ?: false
}

/** Returns true if the device has an available front camera. False otherwise */
fun hasFrontCamera(cameraProvider: ProcessCameraProvider?): Boolean {
    return cameraProvider?.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA) ?: false
}


fun previewBuilder(screenAspectRatio: Int, rotation: Int) =
    Preview.Builder()
        .setTargetAspectRatio(screenAspectRatio)
        .setTargetRotation(rotation)
        .build()

fun imageCaptureBuilder(screenAspectRatio: Int, rotation: Int, flashMode: Int) = ImageCapture.Builder()
    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
    .setTargetAspectRatio(screenAspectRatio)
    .setTargetRotation(rotation)
    .setFlashMode(flashMode)
    .build()

fun imageAnalysisBuilder(screenAspectRatio: Int, rotation: Int, cameraExecutor: ExecutorService) = ImageAnalysis.Builder()
    .setTargetAspectRatio(screenAspectRatio)
    .setTargetRotation(rotation)
    .build()
    .also {
        it.setAnalyzer(cameraExecutor, LuminosityAnalyzer { luma ->
            //Log.d(TAG, "Average luminosity: $luma")
        })
    }

typealias LumaListener = (luma: Double) -> Unit

/**
 * Our custom image analysis class.
 *
 * <p>All we need to do is override the function `analyze` with our desired operations. Here,
 * we compute the average luminosity of the image by looking at the Y plane of the YUV frame.
 */
private class LuminosityAnalyzer(listener: LumaListener? = null) : ImageAnalysis.Analyzer {
    private val frameRateWindow = 8
    private val frameTimestamps = ArrayDeque<Long>(5)
    private val listeners = ArrayList<LumaListener>().apply { listener?.let { add(it) } }
    private var lastAnalyzedTimestamp = 0L
    var framesPerSecond: Double = -1.0
        private set

    /**
     * Used to add listeners that will be called with each luma computed
     */
    fun onFrameAnalyzed(listener: LumaListener) = listeners.add(listener)

    /**
     * Helper extension function used to extract a byte array from an image plane buffer
     */
    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()    // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data)   // Copy the buffer into a byte array
        return data // Return the byte array
    }

    /**
     * Analyzes an image to produce a result.
     *
     * <p>The caller is responsible for ensuring this analysis method can be executed quickly
     * enough to prevent stalls in the image acquisition pipeline. Otherwise, newly available
     * images will not be acquired and analyzed.
     *
     * <p>The image passed to this method becomes invalid after this method returns. The caller
     * should not store external references to this image, as these references will become
     * invalid.
     *
     * @param image image being analyzed VERY IMPORTANT: Analyzer method implementation must
     * call image.close() on received images when finished using them. Otherwise, new images
     * may not be received or the camera may stall, depending on back pressure setting.
     *
     */
    override fun analyze(image: ImageProxy) {
        // If there are no listeners attached, we don't need to perform analysis
        if (listeners.isEmpty()) {
            image.close()
            return
        }

        // Keep track of frames analyzed
        val currentTime = System.currentTimeMillis()
        frameTimestamps.push(currentTime)

        // Compute the FPS using a moving average
        while (frameTimestamps.size >= frameRateWindow) frameTimestamps.removeLast()
        val timestampFirst = frameTimestamps.peekFirst() ?: currentTime
        val timestampLast = frameTimestamps.peekLast() ?: currentTime
        framesPerSecond = 1.0 / ((timestampFirst - timestampLast) /
                frameTimestamps.size.coerceAtLeast(1).toDouble()) * 1000.0

        // Analysis could take an arbitrarily long amount of time
        // Since we are running in a different thread, it won't stall other use cases

        lastAnalyzedTimestamp = frameTimestamps.first

        // Since format in ImageAnalysis is YUV, image.planes[0] contains the luminance plane
        val buffer = image.planes[0].buffer

        // Extract image data from callback object
        val data = buffer.toByteArray()

        // Convert the data into an array of pixel values ranging 0-255
        val pixels = data.map { it.toInt() and 0xFF }

        // Compute average luminance for the image
        val luma = pixels.average()

        // Call all listeners with new value
        listeners.forEach { it(luma) }

        image.close()
    }
}
