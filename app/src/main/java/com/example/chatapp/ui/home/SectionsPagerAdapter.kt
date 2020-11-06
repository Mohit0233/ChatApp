package com.example.chatapp.ui.home

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int) = when (position) {
        0 -> CameraTabFragment.newInstance()
        1 -> ChatsFragment.newInstance()
        else -> PlaceholderFragment.newInstance(position + 1)
    }
}