package com.example.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.R
import com.example.chatapp.ui.chatroom.ChatRoomActivity
import com.example.chatapp.ui.chatroom.ChatRoomActivity.Companion.CHAT_ID

class ContactPickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_picker)

        supportActionBar?.title = "Select Contact"
        supportActionBar?.subtitle = "0 Contacts"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val enterCustomContact = findViewById<TextView>(R.id.enterCustomContact)
        val submitContactPicker = findViewById<Button>(R.id.submitContactPicker)

        submitContactPicker.setOnClickListener {
            val str = enterCustomContact.text.toString()
            if (str.length == 13) {
                Intent(this, ChatRoomActivity::class.java).apply {
                    putExtra(CHAT_ID,str)
                    startActivity(this)
                }
            }
        }
    }
}