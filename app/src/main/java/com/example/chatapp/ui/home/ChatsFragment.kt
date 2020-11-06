package com.example.chatapp.ui.home

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chatapp.R
import kotlinx.android.synthetic.main.chats_fragment.*
import kotlinx.android.synthetic.main.chats_fragment.view.*

class ChatsFragment : Fragment() {

    companion object {
        fun newInstance() = ChatsFragment()
    }

    private lateinit var viewModel: ChatsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(requireContext(), "onCreatedView", Toast.LENGTH_SHORT).show()
        val root = inflater.inflate(R.layout.chats_fragment, container, false)
        root.textView.append("onCreatedView\n")
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView.append("onActivityCreated\n")
        viewModel = ViewModelProvider(this).get(ChatsViewModel::class.java)
        Toast.makeText(requireContext(), "onActivityCreated", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        textView.append("onStart\n")
    }

    override fun onResume() {
        super.onResume()
        textView.append("onResume\n")
    }

    override fun onPause() {
        super.onPause()
        if (textView != null)
        textView.append("onPause\n")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(context, "onStop", Toast.LENGTH_SHORT).show()
        Log.e("OnStop", "Hello")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(context, "onDestroyView", Toast.LENGTH_SHORT).show()
        Log.e("onDestroyView", "Hello")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(requireContext(), "onDestroy", Toast.LENGTH_SHORT).show()
        Log.e("onDestroy", "Hello")
    }

    override fun onDetach() {
        super.onDetach()
        Toast.makeText(requireContext(), "onDetach", Toast.LENGTH_SHORT).show()
    }



}