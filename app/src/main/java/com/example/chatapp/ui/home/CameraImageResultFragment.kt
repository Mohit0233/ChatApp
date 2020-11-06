package com.example.chatapp.ui.home

 import android.os.Bundle
 import android.view.LayoutInflater
 import android.view.View
 import android.view.ViewGroup
 import android.widget.ImageView
 import androidx.fragment.app.Fragment
 import androidx.navigation.fragment.navArgs
 import com.bumptech.glide.Glide
 import com.example.chatapp.R

class CameraImageResultFragment : Fragment() {

    private val args: CameraImageResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera_image_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uri = args.uri
       val capturedImage = view.findViewById<ImageView>(R.id.capturedImage)

        // Run the operations in the view's thread
        capturedImage.post {
            // Load thumbnail into circular button using Glide
            Glide.with(capturedImage)
                .load(uri)
                .into(capturedImage)
        }
    }
}