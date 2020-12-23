package com.example.chatapp.ui.chatroom

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.ui.chatinfo.ChatInfoActivity
import com.example.chatapp.ui.chatinfo.ChatInfoActivity.Companion.DISPLAY_PICTURE
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.util.Pair


class ChatRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        setSupportActionBar(findViewById(R.id.topAppBar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)


        //val drawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.ic_photo_camera)
        //val d: Drawable = BitmapDrawable(resources, )
        /*
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.large_icon)
        notBuilder.setLargeIcon(largeIcon)*/

        val recyclerView = findViewById<RecyclerView>(R.id.messageRecyclerView)
        recyclerView.setHasFixedSize(true)


        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        recyclerView.layoutManager = layoutManager
        val adapter = MessageAdapter()
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(10)
/*
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dx <= recyclerView)
            }

        })*/

        val scrollToBottomFab = findViewById<FloatingActionButton>(R.id.scrollToBottomFab)
        val chatInfoConstraintLayout = findViewById<ConstraintLayout>(R.id.chatInfoConstraintLayout)
        val chatRoomDisplayImageView = findViewById<ImageView>(R.id.ChatRoomDisplayImageView)
        scrollToBottomFab.visibility = View.VISIBLE  // Todo correct it

        scrollToBottomFab.setOnClickListener {
            //Todo disable smooth scroll because it's not it main app
            recyclerView.smoothScrollToPosition(0) // Todo correct it
        }

        chatInfoConstraintLayout.setOnClickListener {

            val intent = Intent(
                this,
                ChatInfoActivity::class.java
            )
            //intent.putExtra(ChatInfoActivity.POSITION_PRODUCT_ITEM, position)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair(
                    chatRoomDisplayImageView,
                    DISPLAY_PICTURE
                )
            )

            startActivity(intent, options.toBundle())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.chat_room_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.groupCallItem -> {
            Toast.makeText(this, "Group Call", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.groupInfoItem -> {
            Toast.makeText(this, "Group Info ", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.groupMediaItem -> {

            true
        }
        R.id.searchItem -> {

            true
        }
        R.id.muteNotificationItem -> {
            true
        }
        R.id.wallpaperItem -> {
            true
        }
        R.id.moreItem -> {
            true
        }
        R.id.reportItem -> {
            true
        }
        R.id.exitGroupItem -> {
            true
        }
        R.id.addShortcutItem -> {
            true
        }
        R.id.clearChatItem -> {
            true
        }
        R.id.exportChatItem -> {
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}