package com.example.chatapp.ui.home

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowInsets
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.viewpager2.widget.ViewPager2
import com.example.chatapp.R
import com.example.chatapp.ui.home.fragment.PermissionsDialogFragment
import com.example.chatapp.ui.home.fragment.PermissionsFragment
import com.example.chatapp.ui.home.adapter.SectionsPagerAdapter
import com.example.chatapp.utils.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import java.io.File


const val KEY_EVENT_ACTION = "key_event_action"
const val KEY_EVENT_EXTRA = "key_event_extra"
private const val IMMERSIVE_FLAG_TIMEOUT = 500L

private const val PERMISSIONS_REQUEST_CODE = 10
private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA)


/** Use external media if it is available, our app's file directory otherwise */
class HomeActivity : AppCompatActivity(), PermissionsDialogFragment.PermissionsDialogListener,
    PermissionsDialogFragment.PermissionsDialogBackListener {

    private lateinit var viewPager: ViewPager2
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private val tabNames = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3
    )
    private val relativeDensity by lazy {
        resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tabLayout: TabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)

        sectionsPagerAdapter = SectionsPagerAdapter(this)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.setCurrentItem(1, false)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.icon = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_baseline_photo_camera_24,
                    theme
                )
                else -> tab.text = getString(tabNames[position - 1])
            }
        }.attach()

        tabLayout.setTabWidthAsWrapContent(0)  //custom extension function

        val appBar = findViewById<AppBarLayout>(R.id.appBar)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val fab2: FloatingActionButton = findViewById(R.id.fab2)


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                appBar?.hideAppBar(position, positionOffset)

                if (position == 0) {
                    if (fab.translationY < 16 * relativeDensity) {
                        fab.translationX =
                            (resources.displayMetrics.widthPixels - positionOffsetPixels).toFloat()
                        fab2.translationX =
                            (resources.displayMetrics.widthPixels - positionOffsetPixels).toFloat()
                    }
                }
            }
        })

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab) {
                    tabLayout.getTabAt(0) -> {
                        fab2.translationAnimation(0 * relativeDensity)
                        fab2.postOnAnimation { fab2.visibility = View.INVISIBLE }
                        showImmersive()
                    }
                    tabLayout.getTabAt(1) -> {
                        this@HomeActivity.setDrawable(fab, R.drawable.ic_baseline_chat_24)
                        fab2.translationAnimation(0 * relativeDensity)
                        exitImmersive()
                    }
                    tabLayout.getTabAt(2) -> {
                        this@HomeActivity.setDrawable(fab, R.drawable.ic_baseline_photo_camera_24)
                        fab2.visibility = View.VISIBLE
                        fab2.translationAnimation(-64 * relativeDensity)
                        exitImmersive()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        fab.setOnClickListener {
            when (tabLayout.selectedTabPosition) {
                0 -> {}
                1 -> {
                    Intent(this, ContactPickerActivity::class.java).apply {
                        startActivity(this)
                    }
                }
                3 -> {}
            }
        }
        fab2.setOnClickListener {

            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    @Suppress("DEPRECATION")
    fun showImmersive() {
        viewPager.postDelayed({
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(false)
                window.insetsController?.hide(WindowInsets.Type.systemBars())
            }
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
                window.decorView.systemUiVisibility = FLAGS_FULLSCREEN
            }
        }, IMMERSIVE_FLAG_TIMEOUT)
    }

    fun exitImmersive() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(true)
        } else if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
            window.decorView.systemUiVisibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        if (viewPager.currentItem == 0) {
            showImmersive()
        } else {
            exitImmersive()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                val intent = Intent(KEY_EVENT_ACTION).apply { putExtra(KEY_EVENT_EXTRA, keyCode) }
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        if (!PermissionsFragment.hasPermissions(this)) {
            // Request camera-related permissions
            requestPermissions(PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE)
            //Todo add read phone state permission READ_PHONE_STATE
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        viewPager.currentItem = 1
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (PackageManager.PERMISSION_GRANTED == grantResults.firstOrNull()) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                viewPager.currentItem = 1
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun backPress(dialog: DialogInterface) {
        Toast.makeText(this, "ckPressed", Toast.LENGTH_SHORT).show()
        Log.e("onBackPressed", "ckPressed")
        dialog.dismiss()
        viewPager.currentItem = 1
    }

    companion object {
        fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }
    }
}