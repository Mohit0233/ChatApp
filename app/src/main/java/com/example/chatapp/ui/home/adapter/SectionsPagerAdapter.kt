package com.example.chatapp.ui.home.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chatapp.ui.home.fragment.CameraTabFragment
import com.example.chatapp.ui.home.fragment.ChatsFragment
import com.example.chatapp.ui.home.fragment.PlaceholderFragment

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int) = when (position) {
        0 -> CameraTabFragment.newInstance()
        1 -> ChatsFragment.newInstance()
        else -> PlaceholderFragment.newInstance(position + 1)
    }
}