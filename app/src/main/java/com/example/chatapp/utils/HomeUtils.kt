package com.example.chatapp.utils

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

fun AppBarLayout.hideAppBar(position: Int, positionOffset: Float) {
    if (position == 0) {
        var mBottom = this.bottom.toFloat()
        var y = positionOffset * mBottom - mBottom
        if (y == -mBottom) {
            val h = this.height
            if (mBottom < h) mBottom = h.toFloat()
            y = -mBottom - mBottom / 8f
        }
        this.translationY = y
    } else if (this.translationY != 0f) this.translationY = 0f
}

fun TabLayout.setTabWidthAsWrapContent(tabPosition: Int) {
    val layout = (this.getChildAt(0) as LinearLayout).getChildAt(tabPosition) as LinearLayout
    val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
    layoutParams.weight = 0f
    layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
    layout.layoutParams = layoutParams
}


fun Context.setDrawable(fab: FloatingActionButton, drawableId: Int) {
    fab.setImageDrawable(
        ContextCompat.getDrawable(
            this,
            drawableId
        )
    )
}

fun View.translationAnimation(displayPixel: Float) {
    this.animate()
        .translationY(displayPixel)
        .setDuration(100)
        .start()
}