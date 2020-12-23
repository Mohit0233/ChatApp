package com.example.chatapp.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.ui.chatroom.ChatRoomActivity
import com.example.chatapp.ui.home.viewmodel.ChatsViewModel
import kotlinx.android.synthetic.main.chats_fragment.*

class ChatsFragment : Fragment() {

    companion object {
        fun newInstance() = ChatsFragment()
    }

    private lateinit var viewModel: ChatsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(requireContext(), "onCreatedView", Toast.LENGTH_SHORT).show()
        val root = inflater.inflate(R.layout.chats_fragment, container, false)
        //root.button.append("onCreatedView\n")
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //button.append("onActivityCreated\n")
        viewModel = ViewModelProvider(this).get(ChatsViewModel::class.java)
        Toast.makeText(requireContext(), "onActivityCreated", Toast.LENGTH_SHORT).show()
        button.setOnClickListener {
            startActivity(Intent(requireActivity(),ChatRoomActivity::class.java))
        }
    }


}