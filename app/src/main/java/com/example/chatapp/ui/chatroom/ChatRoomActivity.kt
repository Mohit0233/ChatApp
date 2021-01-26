package com.example.chatapp.ui.chatroom

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.data.db.MsgStore
import com.example.chatapp.data.firebase.FirebaseAuthSource
import com.example.chatapp.data.firebase.FirebaseFirestoreSource
import com.example.chatapp.ui.base.ViewModelFactory
import com.example.chatapp.ui.chatinfo.ChatInfoActivity
import com.example.chatapp.ui.chatinfo.ChatInfoActivity.Companion.DISPLAY_PICTURE
import com.example.chatapp.ui.home.fragment.ChatChannel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.*
import java.util.*


class ChatRoomActivity : AppCompatActivity(), MessageAdapter.MessageOnItemClickListener {

    private lateinit var messageAdapter: MessageAdapter//Todo init it
    private lateinit var firebaseAuthSource: FirebaseAuthSource
    private lateinit var firebaseFirestoreSource: FirebaseFirestoreSource
    private lateinit var chatChannelsCollectionRef: CollectionReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var messagesListenerRegistration: ListenerRegistration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        setSupportActionBar(findViewById(R.id.topAppBar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)


        firebaseFirestoreSource = FirebaseFirestoreSource()
        firebaseAuthSource = FirebaseAuthSource(null)
        chatChannelsCollectionRef = firebaseFirestoreSource.getDatabase().collection("chatChannels")
        val scrollToBottomFab = findViewById<FloatingActionButton>(R.id.scrollToBottomFab)
        val chatInfoConstraintLayout = findViewById<ConstraintLayout>(R.id.chatInfoConstraintLayout)
        val chatRoomDisplayImageView = findViewById<ImageView>(R.id.chatRoomDisplayImageView)

        val navigationBackButtonChatRoom =
            findViewById<ConstraintLayout>(R.id.navigationBackButtonChatRoom)
        val appbarTitleTextViewChatRoom = findViewById<TextView>(R.id.titleTextViewChatRoom)
        val subtitleTextViewChatRoom = findViewById<TextView>(R.id.subtitleTextViewChatRoom)
        val footerConstraintLayout = findViewById<ConstraintLayout>(R.id.footerConstraintLayout)
        val sendButtonMain = findViewById<ImageView>(R.id.sendButtonMain)
        val emojiButtonChatRoom = findViewById<ImageView>(R.id.emojiButtonChatRoom)
        val mainEditText = findViewById<EditText>(R.id.mainEditText)
        val attachButton = findViewById<ImageView>(R.id.attachButton)
        val cameraButton = findViewById<ImageView>(R.id.cameraButton)


        navigationBackButtonChatRoom.setOnClickListener {
            onBackPressed()
        }

        //val drawable: Drawable? = ContextCompat.getDrawable(this, R.drahowable.ic_photo_camera)
        //val d: Drawable = BitmapDrawable(resources, )
        /*
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.large_icon)
        notBuilder.setLargeIcon(largeIcon)*/

        val chatRoomViewModel = ViewModelProvider(
            this,
            ViewModelFactory(FirebaseAuthSource(null), MsgStore.getDatabase(this), null)
        )

        recyclerView = findViewById<RecyclerView>(R.id.messageRecyclerView)
        recyclerView.setHasFixedSize(true)
        messageAdapter = MessageAdapter(this, this) //Todo init here

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = messageAdapter
        recyclerView.setHasFixedSize(true)

        /*
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dx <= recyclerView)
            }

        })*/

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

        //id Mohit Kumar-1611689844659Mohit Kumar-1611689844659

        val otherUserId = intent.getStringExtra(CHAT_ID)!!
        Log.e("1. other user id", otherUserId)
        getOrCreateChatChannel(otherUserId) { channelId ->
            //currentChannelId = channelId

            messagesListenerRegistration =
                addChatMessagesListener(channelId, this, this::updateRecyclerView)

            Log.e("2. messageListenerRegistration", otherUserId)


            sendButtonMain.setOnClickListener {
                val messageToSend =
                    Msg(
                        firebaseAuthSource.currentUser()!!.uid + System.currentTimeMillis(),
                        "Mohit",
                        mainEditText.text.toString(),
                        System.currentTimeMillis(),
                        firebaseAuthSource.currentUser()!!.uid,
                        otherUserId,
                        1,
                        0,
                        0
                    )
                // todo now you change the read receipts
                mainEditText.setText("")
                sendMessage(messageToSend, channelId)
            }

            attachButton.setOnClickListener {
                val intent = Intent().apply {
                    type = "image/*"
                    action = Intent.ACTION_GET_CONTENT
                    putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
                }
                startActivityForResult(
                    Intent.createChooser(intent, "Select Image"),
                    RC_SELECT_IMAGE
                )
            }
        }

        //getting messages from view model
        //observeMessages(chatRoomViewModel.getAllMsg())

    }

    private fun updateRecyclerView(messages: List<Msg>) {

        Log.e("updateRecyclerView msgv 😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀😀", "")
        for (message in messages) {
            if (message.senderId == firebaseAuthSource.currentUser()!!.uid) {
                message.keyFromMe = 1
            } else {
                message.keyFromMe = 0
            }
            Log.e("183", message.data)
        }
        //messages.add(Msg("12345","Name","System.currentTimeMillis()",System.currentTimeMillis(),"123", "345",1,0,0 ))
        messageAdapter.addList(messages)
        recyclerView.scrollToPosition(recyclerView.adapter?.itemCount?.minus(1)!!)
    }


    fun sendMessage(message: Msg, channelId: String) {
        chatChannelsCollectionRef.document(channelId)
            .collection("messages")
            .add(message)
    }


    fun getOrCreateChatChannel(
        otherUserId: String,
        onComplete: (channelId: String) -> Unit
    ) {
        val currentUserDocRef = firebaseFirestoreSource.getDatabase()
            .collection("users").document("${firebaseAuthSource.currentUser()?.uid}")

        currentUserDocRef.collection("engagedChatChannels")
            .document(otherUserId).get().addOnSuccessListener {
                if (it.exists()) {
                    onComplete(it["channelId"] as String)
                    return@addOnSuccessListener
                }

                val currentUserId = firebaseAuthSource.currentUser()?.uid!!

                val newChannel = chatChannelsCollectionRef.document()
                newChannel.set(ChatChannel(mutableListOf(currentUserId, otherUserId)))
                newChannel.set(
                    hashMapOf(
                        "id" to otherUserId,
                        "displayPicture" to null,
                        "title" to "Group" + System.currentTimeMillis().toString().takeLast(2),
                        "lastMsg" to null,
                        "personOfLastMsg" to null,
                        "last_msg_type" to null,
                        "timestamp" to System.currentTimeMillis(),
                        "noOfUnreadMsg" to null,
                        "isLiveLocationEnabled" to false,
                        "isMuted" to false,
                        "isPinned" to false,
                        "ephemeralStatus" to false,
                    ) as Map<String, Any>, SetOptions.merge()
                )
                currentUserDocRef
                    .collection("engagedChatChannels")
                    .document(otherUserId)
                    .set(mapOf("channelId" to newChannel.id))

                firebaseFirestoreSource.getDatabase().collection("users").document(otherUserId)
                    .collection("engagedChatChannels")
                    .document(currentUserId)
                    .set(mapOf("channelId" to newChannel.id))

                onComplete(newChannel.id)
            }
    }


    fun addChatMessagesListener(
        channelId: String, context: Context,
        onListen: (List<Msg>) -> Unit
    ): ListenerRegistration {
        Log.e("3 addChatMessagesListener 😀😀😀😀😀😀😀😀", channelId)

        return chatChannelsCollectionRef.document(channelId).collection("messages")
            .orderBy("time")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("FIRESTORE", "ChatMessagesListener error.", firebaseFirestoreException)
                    return@addSnapshotListener
                }
                val items = mutableListOf<Msg>()
                Log.e("4. other user id", querySnapshot.toString())
                if (querySnapshot != null) {
                    Log.e("querySnapshot != ", "null")
                    Log.e("querySnapshot.documents != ", "null")
                    querySnapshot.documents.forEach {

                        Log.e("5. No. of messages", items.size.toString())
                        items.add(it.toObject(Msg::class.java)!!)
                        Log.e("6. No. of messages", items.size.toString())

                    }
                    Log.e("",querySnapshot.documents.size.toString())
                    //Log.e("",querySnapshot.documents[0].get("name"))
                } else {
                    Log.e("querySnapshot equals ", "null")
                }



                onListen(items)
            }
    }

/*
private fun observeMessages(liveData: LiveData<List<Msg>>) {
        // Update the list when the data changes
        liveData.observe(this,
            { messages: List<Msg>? ->
                if (messages != null) {
                    //mBinding.setIsLoading(false)
                    messageAdapter.setMessageList(messages)
                } else {
                    //mBinding.setIsLoading(true)
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                //mBinding.executePendingBindings()
            })
    }
*/

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

    override fun onDestroy() {
        super.onDestroy()
        if (this::messagesListenerRegistration.isInitialized)
            removeListener(messagesListenerRegistration)
    }

    fun removeListener(registration: ListenerRegistration) = registration.remove()

    companion object {
        const val CHAT_ID = "CHAT_ID"
        private const val RC_SELECT_IMAGE = 2
    }

    override fun onItemClick() {

    }

    override fun onLongClick() {

    }
}

data class Msg(
    var id: String,
    var name: String?,
    var data: String,
    var timestamp: Long,
    var senderId: String,
    var reseiverId: String,
    var keyFromMe: Int,
    var isMedia: Int,
    var receipt: Int,
)