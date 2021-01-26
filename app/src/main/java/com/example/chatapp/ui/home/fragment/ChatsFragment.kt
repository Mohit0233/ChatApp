package com.example.chatapp.ui.home.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.example.chatapp.data.firebase.FirebaseAuthSource
import com.example.chatapp.data.firebase.FirebaseFirestoreSource
import com.example.chatapp.ui.chatroom.ChatRoomActivity
import com.example.chatapp.ui.chatroom.ChatRoomActivity.Companion.CHAT_ID
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.Comparator

class ChatsFragment : Fragment(), ChatListAdapter.ChatListAdapterOnItemClickListener {


    /*private lateinit var viewModel: ChatsViewModel*/
    private lateinit var firebaseAuthSource: FirebaseAuthSource
    private lateinit var firebaseFirestoreSource: FirebaseFirestoreSource
    private lateinit var chatChannelsCollectionRef: CollectionReference
    private lateinit var userListenerRegistration: ListenerRegistration
    lateinit var chatListAdapter: ChatListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(requireContext(), "onCreatedView", Toast.LENGTH_SHORT).show()
        val root = inflater.inflate(R.layout.chats_fragment, container, false)
        //root.button.append("onCreatedView\n")
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewChatFragment)
        chatListAdapter = ChatListAdapter(requireContext(), this)
        val layout = LinearLayoutManager(requireContext())
        recyclerView.apply {
            layoutManager = layout
            itemAnimator = null
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                ).apply {
                    setDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.divider_gray
                        )!!
                    )
                })
            adapter = chatListAdapter
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //button.append("onActivityCreated\n")
/*
        viewModel = ViewModelProvider(this, ViewModelFactory(FirebaseAuthSource(null), MsgStore, null))
            .get(ChatsViewModel::class.java)*/

        firebaseFirestoreSource = FirebaseFirestoreSource()
        firebaseAuthSource = FirebaseAuthSource(null)
        chatChannelsCollectionRef = firebaseFirestoreSource.getDatabase().collection("chatChannels")


        Log.e("", "hi")
        userListenerRegistration = addUsersListener(requireActivity(), this::updateRecyclerView)

        Log.e("", "by")
        //startActivity(Intent(requireActivity(), ChatRoomActivity::class.java))
    }

    fun addUsersListener(
        context: Context,
        onListen: (List<ChatListModel>) -> Unit
    ): ListenerRegistration {
        return firebaseFirestoreSource.getDatabase().collection("users")
            .document(firebaseAuthSource.currentUser()?.uid.toString())
            .collection("engagedChatChannels")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("FIRESTORE", "Users listener error.", firebaseFirestoreException)
                    Toast.makeText(requireContext(), "users listener error", Toast.LENGTH_SHORT)
                        .show()
                    return@addSnapshotListener
                }
                Log.e("", "Working                 ")
                val items = mutableListOf<String?>()
                querySnapshot!!.documents.forEach {
                    items.add(it.get("channelId") as String?)
                }
                for (item in items) {
                    if (item != null) {
                        Log.e("itemmmmmmmmmmmmmmmmmmmmmmm", item)
                        firebaseFirestoreSource.getDatabase().collection("chatChannels")
                            .document(item).get()
                            .addOnSuccessListener { document ->
                                if (document != null && document.data != null) {
                                    Log.e(
                                        "TAG",
                                        "DocumentSnapshot data:  ${document.get("displayPicture")}"
                                    )
                                    val chatListItems = mutableListOf<ChatListModel>()
                                    chatListItems.add(
                                        ChatListModel(
                                            document.get("id") as String?,
                                            document.get("displayPicture") as String?,
                                            document.get("title") as String?,
                                            document.get("lastMsg") as String?,
                                            document.get("personOfLastMsg") as String?,
                                            document.get("last_msg_type") as String?,
                                            document.get("timestamp") as Long?,
                                            document.get("noOfUnreadMsg") as Int?,
                                            document.get("isLiveLocationEnabled") as Boolean,
                                            document.get("isMuted") as Boolean,
                                            document.get("isPinned") as Boolean,
                                            document.get("ephemeralStatus") as Boolean,
                                        )
                                    )
                                    onListen(chatListItems)
                                } else {
                                    Log.e("TAG", "No such document")
                                }
                            }
                            .addOnFailureListener { exception ->
                                Log.e("TAG", "get failed with ", exception)
                            }
                        /*if (firebaseFirestoreException != null) {
                            Log.e(
                                "FIRESTORE",
                                "chatChannels doc $item listener error.",
                                firebaseFirestoreException
                            )
                            Toast.makeText(
                                requireContext(),
                                "chatChannels doc $item listener error",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@addSnapshotListener
                        }
                        Log.e("",item + querySnapshot.toString() + querySnapshot?.data)
                        val chatListItems = mutableListOf<ChatListModel>()
                        chatListItems.add(
                            ChatListModel(
                                querySnapshot!!.get("id") as String?,
                                querySnapshot.get("displayPicture") as Bitmap?,
                                querySnapshot.get("title") as String?,
                                querySnapshot.get("lastMsg") as String?,
                                querySnapshot.get("personOfLastMsg") as String?,
                                querySnapshot.get("last_msg_type") as String?,
                                querySnapshot.get("timestamp") as Long?,
                                querySnapshot.get("noOfUnreadMsg") as Int?,
                                querySnapshot.get("isLiveLocationEnabled") as Boolean,
                                querySnapshot.get("isMuted") as Boolean,
                                querySnapshot.get("isPinned") as Boolean,
                                querySnapshot.get("ephemeralStatus") as Boolean,
                            )
                        )
                        onListen(chatListItems)
                    }*/
                    }
                }

            }
    }

    private fun updateRecyclerView(items: List<ChatListModel>) {
        chatListAdapter.addList(items)
    }


    override fun onItemClick(chatId: String) {
        Intent(requireActivity(), ChatRoomActivity::class.java).apply {
            putExtra(CHAT_ID, chatId)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeListener(userListenerRegistration)
    }

    fun removeListener(registration: ListenerRegistration) = registration.remove()

    companion object {
        fun newInstance() = ChatsFragment()
    }


}


class ChatListAdapter(
    private var context: Context,
    var listener: ChatListAdapterOnItemClickListener
) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    private var chatSortedList: SortedList<ChatListModel> = SortedList(ChatListModel::class.java,
        object : SortedList.Callback<ChatListModel>() {
            override fun compare(o1: ChatListModel?, o2: ChatListModel?): Int {
                return o1?.title?.compareTo(o2?.title!!)!!//todo change it
            }

            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position, count)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onChanged(position: Int, count: Int) {
                notifyItemRangeChanged(position, count)
            }

            override fun areContentsTheSame(
                oldItem: ChatListModel?,
                newItem: ChatListModel?
            ): Boolean {
                return oldItem?.title == newItem?.title
            }

            override fun areItemsTheSame(
                item1: ChatListModel?,
                item2: ChatListModel?
            ): Boolean {
                return item1?.id == item2?.id
            }

        })

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var displayPictureChatView: ShapeableImageView =
            itemView.findViewById<ShapeableImageView>(R.id.displayPictureChatView)
        var conversations_row_ephemeral_status: ImageView =
            itemView.findViewById<ImageView>(R.id.conversations_row_ephemeral_status)
        var selection_check: CheckBox = itemView.findViewById<CheckBox>(R.id.selection_check)
        var payments_indicator: TextView = itemView.findViewById<TextView>(R.id.payments_indicator)
        var live_location_indicator: ImageView =
            itemView.findViewById<ImageView>(R.id.live_location_indicator)
        var titleChatView: TextView = itemView.findViewById<TextView>(R.id.titleChatView)
        var timeStampChatView: TextView = itemView.findViewById<TextView>(R.id.timeStampChatView)
        var receiptsChatView: ImageView = itemView.findViewById<ImageView>(R.id.receiptsChatView)
        var meidaIndicator: ImageView = itemView.findViewById<ImageView>(R.id.meidaIndicator)
        var descriptionChatView: TextView =
            itemView.findViewById<TextView>(R.id.descriptionChatView)
        var pinImageViewChatView: ImageView =
            itemView.findViewById<ImageView>(R.id.pinImageViewChatView)
        var muteIndicator: ImageView = itemView.findViewById<ImageView>(R.id.muteIndicator)
        var unseenCountChatView: TextView =
            itemView.findViewById<TextView>(R.id.unseenCountChatView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                Log.e("", chatSortedList[position].id!!)
                listener.onItemClick(chatSortedList[position].id!!)
            }
        }
    }

    fun add(model: ChatListModel?) {
        chatSortedList.add(model)
    }

    fun remove(model: ChatListModel?) {
        chatSortedList.remove(model)
    }

    fun addList(models: List<ChatListModel?>?) {
        chatSortedList.addAll(models!!)
    }

    fun remove(models: List<ChatListModel?>) {
        chatSortedList.beginBatchedUpdates()
        for (model in models) {
            chatSortedList.remove(model)
        }
        chatSortedList.endBatchedUpdates()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_chat_view, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatListModel: ChatListModel = chatSortedList[position]
        if (chatListModel.displayPicture != null){

            Glide.with(holder.displayPictureChatView)
                .load(chatListModel.displayPicture)
                .into(holder.displayPictureChatView)
        } else {
            Glide.with(holder.displayPictureChatView)
                .load(R.drawable.avatar_contact)
                .into(holder.displayPictureChatView)
        }
        holder.titleChatView.text = chatListModel.title
        val description =  chatListModel.lastMsg
        holder.descriptionChatView.text = description
        //holder.meidaIndicator.setImageBitmap() = chatListModel.last_msg_type //todo
        holder.timeStampChatView.text = if (chatListModel.timestamp != null) longToTimeConvertor(
            chatListModel.timestamp!!
        ) else ""
        holder.unseenCountChatView.text = chatListModel.noOfUnreadMsg.toString()
        holder.live_location_indicator.visibility =
            if (chatListModel.isLiveLocationEnabled) View.VISIBLE else View.INVISIBLE
        holder.muteIndicator.visibility =
            if (chatListModel.isMuted) View.VISIBLE else View.INVISIBLE
        holder.pinImageViewChatView.visibility =
            if (chatListModel.isPinned) View.VISIBLE else View.INVISIBLE
        holder.conversations_row_ephemeral_status.visibility =
            if (chatListModel.ephemeralStatus) View.VISIBLE else View.INVISIBLE
    }

    override fun getItemCount() = chatSortedList.size()

    interface ChatListAdapterOnItemClickListener {
        fun onItemClick(chatId: String)
    }

}

fun longToTimeConvertor(timeInLong: Long): String = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(java.lang.Long.valueOf(timeInLong)), ZoneId.of("Asia/Kolkata")
).format(DateTimeFormatter.ofPattern("hh:mm a"))

private val ALPHABETICAL_COMPARATOR: Comparator<ChatListModel> = Comparator { a, b ->
    a.timestamp!!.compareTo(b.timestamp!!)
}

data class ChatListModel(
    var id: String?,
    var displayPicture: String?,
    var title: String?,
    var lastMsg: String?,
    var personOfLastMsg: String?,
    var last_msg_type: String?,
    var timestamp: Long?,
    var noOfUnreadMsg: Int?,
    var isLiveLocationEnabled: Boolean,
    var isMuted: Boolean,
    var isPinned: Boolean,
    var ephemeralStatus: Boolean,
) {
    constructor() : this("", null, "", null, null, null, null, null, false, false, false, false)
}

data class User(
    val displayPicture: String,
    val name: String,
    val timestamp: Long,
    val unknownList: List<Any>
) {
    constructor() : this("", "", 0, mutableListOf())
}


data class ChatChannel(val userIds: MutableList<String>) {
    constructor() : this(mutableListOf())
}