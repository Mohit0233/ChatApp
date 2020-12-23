package com.example.chatapp.ui.chatinfo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat.setTransitionName
import com.example.chatapp.R
import com.google.android.material.appbar.CollapsingToolbarLayout

class ChatInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_info)
        setSupportActionBar(findViewById(R.id.toolbar))
        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        toolbarLayout.title = "Group Name"
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        toolbar.subtitle = "Created by Guriqbal Singh, 13/11/2018"


        val chatInfoImageView = findViewById<ImageView>(R.id.chatInfoChatImageView)
        setTransitionName(chatInfoImageView, DISPLAY_PICTURE)


    }

    companion object {
        const val DISPLAY_PICTURE = "display_picture"
    }
}