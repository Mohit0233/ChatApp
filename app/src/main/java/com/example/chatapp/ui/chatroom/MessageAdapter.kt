package com.example.chatapp.ui.chatroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemViewType(position: Int): Int {
        return (position + 4) % 4
    }

    private fun requireLayout(viewType: Int): Int {
        return when (viewType) {
            0 -> R.layout.chat_row_left_head
            2 -> R.layout.chat_row_left
            3 -> R.layout.chat_row_right_head
            else -> R.layout.chat_row_right
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(requireLayout(viewType), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() = 100
}