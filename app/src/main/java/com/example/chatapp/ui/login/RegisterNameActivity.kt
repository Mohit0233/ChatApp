package com.example.chatapp.ui.login

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import com.example.chatapp.R
import com.example.chatapp.data.firebase.FirebaseAuthSource
import com.example.chatapp.data.firebase.FirebaseFirestoreSource
import com.example.chatapp.data.firebase.FirebaseStorageSource
import com.example.chatapp.ui.MainActivity
import com.example.chatapp.utils.startHomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.SetOptions
import java.io.ByteArrayOutputStream


class RegisterNameActivity : AppCompatActivity() {

    //private lateinit var imageUri: Uri
    private lateinit var firebaseStorageSource: FirebaseStorageSource
    private lateinit var firebaseAuthSource: FirebaseAuthSource
    private lateinit var firebaseFirestoreSource: FirebaseFirestoreSource
    private lateinit var changePhotoProgress: ProgressBar
    private lateinit var changePhotoBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_name)


        val title = findViewById<TextView>(R.id.titleRegisterName)
        val titleBottomShadow = findViewById<View>(R.id.title_bottom_shadow)
        val regScrollView = findViewById<ScrollView>(R.id.reg_scroll_view)
        val bizInfoDescription = findViewById<TextView>(R.id.biz_info_description)
        changePhotoBtn = findViewById(R.id.change_photo_btn)
        changePhotoProgress = findViewById(R.id.change_photo_progress)
        val registrationName = findViewById<EditText>(R.id.registration_name)
        val nameCounterTv = findViewById<TextView>(R.id.name_counter_tv)
        val emojiBtn = findViewById<ImageView>(R.id.emoji_btn)
        val acceptButtonTopShadow = findViewById<View>(R.id.accept_button_top_shadow)
        val acceptButtonAndShortcutLayout =
            findViewById<LinearLayout>(R.id.accept_button_and_shortcut_layout)
        val registerNameAccept = findViewById<Button>(R.id.register_name_accept)
        val shortcutLayout = findViewById<RelativeLayout>(R.id.shortcut_layout)
        val cbxAppShortcut = findViewById<CheckBox>(R.id.cbx_app_shortcut)
        val emojiSearchContainer = findViewById<FrameLayout>(R.id.emoji_search_container)

        val dimensionPixelSize =
            resources.getDimensionPixelSize(R.dimen.registration_profile_photo_size)
        val dimension = -1F

        firebaseAuthSource = FirebaseAuthSource(null)
        if (firebaseAuthSource.currentUser()?.photoUrl == null) {

            changePhotoBtn.setImageBitmap(
                drawableForDP(
                    this,
                    R.drawable.ic_reg_addphoto,
                    dimensionPixelSize,
                    dimension
                )
            )
        } else {
            changePhotoProgress.visibility = View.VISIBLE
            Glide.with(this)
                .load(firebaseAuthSource.currentUser()?.photoUrl)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        changePhotoProgress.visibility = View.GONE
                        Toast.makeText(this@RegisterNameActivity, "Failed To load Image", Toast.LENGTH_SHORT).show()
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        changePhotoProgress.visibility = View.GONE
                        return true
                    }

                })
                .override(64, 64)
                .into(changePhotoBtn)


        }

        /*
        firebaseFirestoreSource.getDatabase().runTransaction {
                it.update(docRef, "name",registrationName.text.toString())
                Toast.makeText(this, "${it.get(docRef).get("name")}", Toast.LENGTH_SHORT).show()
            }
        */
        registerNameAccept.setOnClickListener {
            val login = hashMapOf(
                "time" to System.currentTimeMillis(),
                "name" to registrationName.text.toString(),
            )
            val user = firebaseAuthSource.currentUser()

            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(registrationName.text.toString())
                .build()

            user!!.updateProfile(profileUpdates)
            Log.e("77 RegisterNameActivity", "{${registrationName.text}}")
            Log.e("78 RegisterNmaeActivity", login["name"].toString())
            firebaseFirestoreSource.getDatabase()
                .collection("users")
                .document(firebaseAuthSource.currentUser()?.uid.toString()).set(
                    login,
                    SetOptions.merge()
                )
                .addOnSuccessListener {
                    saveData(registrationName.text.toString())
                    this.startHomeActivity()
                }.addOnFailureListener {
                    Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                }
        }

        firebaseStorageSource = FirebaseStorageSource()
        firebaseFirestoreSource = FirebaseFirestoreSource()
        firebaseAuthSource = FirebaseAuthSource(null)

        changePhotoBtn.setOnClickListener {
            // aad a photo
            takePicture()
        }

    }

    fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { pictureIntent ->
            pictureIntent.resolveActivity(packageManager!!)
            startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE)

        }
    }

    fun drawableForDP(context: Context, i: Int, i2: Int, f: Float): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, i)
        val createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888)
        val primarySurfaceColor: Int = getColor(R.color.primary_surface)
        val canvas = Canvas(createBitmap)
        drawable?.setBounds(0, 0, i2, i2)
        if (drawable is BitmapDrawable) {
            val paint = Paint()
            val f2 = i2.toFloat()
            val rectF = RectF(0.0f, 0.0f, f2, f2)
            paint.isAntiAlias = true
            paint.isDither = true
            paint.isFilterBitmap = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = primarySurfaceColor
            if (f >= 0.0f) {
                canvas.drawRoundRect(rectF, f, f, paint)
            } else {
                canvas.drawArc(rectF, 0.0f, 360.0f, true, paint)
            }
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
            canvas.drawBitmap(drawable.bitmap, null as Rect?, rectF, paint)
            return createBitmap
        }
        drawable?.draw(canvas)
        return createBitmap
    }

    fun drawableForDP2(context: Context, bitmap: Bitmap, i2: Int, f: Float): Bitmap? {
        val drawable = BitmapDrawable(resources, bitmap)
        val createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888)
        val primarySurfaceColor: Int = getColor(R.color.primary_surface)
        val canvas = Canvas(createBitmap)
        drawable.setBounds(0, 0, i2, i2)
        val paint = Paint()
        val f2 = i2.toFloat()
        val rectF = RectF(0.0f, 0.0f, f2, f2)
        paint.isAntiAlias = true
        paint.isDither = true
        paint.isFilterBitmap = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = primarySurfaceColor
        if (f >= 0.0f) {
            canvas.drawRoundRect(rectF, f, f, paint)
        } else {
            canvas.drawArc(rectF, 0.0f, 360.0f, true, paint)
        }
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
        canvas.drawBitmap(drawable.bitmap, null as Rect?, rectF, paint)
        return createBitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            uploadImageAndSaveUri(imageBitmap)
        }
    }

    private fun uploadImageAndSaveUri(imageBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()//byte array output stream
        val displayPictureName =
            "${firebaseAuthSource.currentUser()?.uid}_${System.currentTimeMillis()}"
        val storageRef = firebaseStorageSource.storage
            .reference
            .child("pics/IMG_${displayPictureName}.jpg")
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()
        val upload = storageRef.putBytes(image)
        changePhotoProgress.visibility = View.VISIBLE
        upload.addOnCompleteListener { uploadTask ->
            if (uploadTask.isSuccessful) {
                changePhotoProgress.visibility = View.VISIBLE
                storageRef.downloadUrl.addOnCompleteListener { urlTask ->
                    urlTask.result?.let {
                        firebaseFirestoreSource.apply {
                            getDocRef(firebaseAuthSource.currentUser()?.uid.toString()).update(
                                hashMapOf(
                                    "displayPicture" to it.toString()
                                ) as Map<String, Any>
                            )
                        }

                        val user = FirebaseAuth.getInstance().currentUser

                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(user?.displayName)
                            .setPhotoUri(it)
                            .build()

                        user!!.updateProfile(profileUpdates)

                        /*
                        Toast.makeText(this, displayPictureName + it.toString(), Toast.LENGTH_SHORT)
                            .show()*/

                        changePhotoBtn.setImageBitmap(
                            drawableForDP2(
                                this,
                                imageBitmap,
                                64,
                                -1F
                            )
                        )
                        changePhotoProgress.visibility = View.GONE
                    }
                }
            } else {
                uploadTask.exception?.let {
                    Toast.makeText(this, it.message!!, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun saveData(name: String) {
        val sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        Log.e("216 Register Name Activity", "{${name}}")
        editor.putString(MainActivity.FLAG_HAVE_NAME, name)
        editor.apply()
    }


    companion object {
        const val REQUEST_IMAGE_CAPTURE = 100
    }
}