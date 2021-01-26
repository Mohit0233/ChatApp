package com.example.chatapp.ui.chatroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.example.chatapp.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MessageAdapter(private val context: Context, private val listener: MessageOnItemClickListener) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private var messagesList: SortedList<Msg> = SortedList(
        Msg::class.java,
        object : SortedList.Callback<Msg>() {
            override fun compare(o1: Msg?, o2: Msg?): Int {
                return o1?.timestamp?.compareTo(o2?.timestamp!!)!!//todo change it
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
                oldItem: Msg?,
                newItem: Msg?
            ): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areItemsTheSame(
                item1: Msg?,
                item2: Msg?
            ): Boolean {
                return item1?.id == item2?.id
            }

        })

    inner class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        val msgData = itemView.findViewById<TextView>(R.id.msgData)
        val msgTime = itemView.findViewById<TextView>(R.id.msgTime)
        var readReceipt:ImageView? = null
        init {
            if (viewType == 1 || viewType == 3) {
                readReceipt = itemView.findViewById<ImageView>(R.id.readReceipt)
            }
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick()
            }
        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onLongClick()
            }
            return true
        }
    }

    fun add(model: Msg?) {
        messagesList.add(model)
    }

    fun remove(model: Msg?) {
        messagesList.remove(model)
    }

    fun addList(models: List<Msg?>?) {
        var arrayList = ArrayList(models)
        arrayList.add(Msg("12345","Name","System.currentTimeMillis()",System.currentTimeMillis(),"123", "345",1,0,0 ))
        arrayList.add(Msg("12346","Name","Hello",System.currentTimeMillis(),"123", "345",0,0,0 ))
        arrayList.add(Msg("12347","Name","Hello",System.currentTimeMillis(),"123", "345",1,0,0 ))
        arrayList.add(Msg("12348","Name","System.currentTimeMillis()",System.currentTimeMillis(),"123", "345",0,0,0 ))
        arrayList.add(Msg("12355","Name","System.currentTimeMillis()",System.currentTimeMillis(),"123", "345",1,0,0 ))
        arrayList.add(Msg("12356","Name","Hello",System.currentTimeMillis(),"123", "345",0,0,0 ))
        arrayList.add(Msg("12357","Name","Hello",System.currentTimeMillis(),"123", "345",1,0,0 ))
        arrayList.add(Msg("12358","Name","System.currentTimeMillis()",System.currentTimeMillis(),"123", "345",0,0,0 ))
        arrayList.add(Msg("12359","Name","System.currentTimeMillis()",System.currentTimeMillis(),"123", "345",1,0,0 ))
        arrayList.add(Msg("16398779","Name","currentTimeMillis()",System.currentTimeMillis(),"125233", "34525",0,0,0 ))
        arrayList.add(Msg("16398773249","Name","currentTimeMillis()",System.currentTimeMillis(),"125233", "34525",1,0,0 ))
        arrayList.add(Msg("16398723479","Name","currentTimeMillis()",System.currentTimeMillis(),"125233", "34525",1,0,0 ))
        arrayList.add(Msg("1987389","Name","System",System.currentTimeMillis(),"123252353563", "342342355",1,0,0 ))
        messagesList.addAll(arrayList!!)
    }

    fun remove(models: List<Msg?>) {
        messagesList.beginBatchedUpdates()
        for (model in models) {
            messagesList.remove(model)
        }
        messagesList.endBatchedUpdates()
    }


    override fun getItemViewType(position: Int): Int {

        return when (messagesList[position].keyFromMe) {
            0 -> {
                if (messagesList[position].keyFromMe == 0) 1 else 3
            }
            1 -> {
                if (messagesList[position].keyFromMe == 0) 0 else 2
            }
            else -> throw IllegalArgumentException(
                "messageTable.keyForMe should be 0 or 1 but " +
                        "${messagesList[position].keyFromMe} found"
            )
        }
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
        return ViewHolder(itemView, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message =  messagesList[position]
        holder.msgData.text = message.data
        holder.msgTime.text = longToTimeConvertor(message.timestamp)
        if (holder.readReceipt != null) {

            if (message.receipt == 1) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_got_read_receipt_from_target
                )
            )
            if (message.receipt == 1) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_got_read_receipt_from_target_onmedia
                )
            )
            if (message.receipt == 1) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_got_receipt_from_server
                )
            )
            if (message.receipt == 0) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_got_receipt_from_server_onmedia
                )
            )
            if (message.receipt == 1) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_got_receipt_from_target
                )
            )
            if (message.receipt == 1) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_got_receipt_from_target_onmedia
                )
            )
            if (message.receipt == 1) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_got_receipt_revoked
                )
            )
            if (message.receipt == 1) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_unsent
                )
            )
            if (message.receipt == 1) holder.readReceipt?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.message_unsent_onmedia
                )
            )

        }

    }

    //override fun getItemId(position: Int) = messageList?.get(position)?.id ?: 0
    override fun getItemCount() = messagesList.size()

    fun longToTimeConvertor(timeInLong: Long): String = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(java.lang.Long.valueOf(timeInLong)), ZoneId.of("Asia/Kolkata")
    ).format(DateTimeFormatter.ofPattern("hh:mm a"))


    interface MessageOnItemClickListener {
        fun onItemClick()
        fun onLongClick()
    }
}