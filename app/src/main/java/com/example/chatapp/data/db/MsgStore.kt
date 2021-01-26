package com.example.chatapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.chatapp.data.db.dao.*
import com.example.chatapp.data.db.entity.*
import com.example.chatapp.data.db.entity.msgstore.*


@Database(
    entities = [
        AwayMessages::class,
        CallLog::class,
        CallLogParticipant::class,
        CallLogParticipantV2::class,
        Chat::class,
        ChatList::class,
        ConversionTuples::class,
        DeletedChatJobs::class,
        Frequent::class,
        Frequents::class,
        GroupNotificationVersion::class,
        GroupParticipantDevice::class,
        GroupParticipants::class,
        GroupParticipantsHistory::class,
        GroupParticipantUser::class,
        InvoiceTransactions::class,
        Jid::class,
        Keywords::class,
        LabeledJid::class,
        LabeledJids::class,
        LabeledMessages::class,
        Lables::class,
        MediaHashThumbnail::class,
        MediaRefs::class,
        MediaStreamingSidecar::class,
        Message::class,
        MessageExternalAdContent::class,
        MessageForwarded::class,
        MessageFuture::class,
        MessageInvoice::class,
        MessageLink::class,
        MessageMediaVcardCount::class,
        MessageMentions::class,
        MessagePayment::class,
        MessagePaymentStatusUpdate::class,
        MessagePaymentTransactionReminder::class,
        MessagePrivacyState::class,
        MessageQuotedGroupInviteLegacy::class,
        MessageQuotedMedia::class,
        MessageQuotedText::class,
        MessageQuotedUiElements::class,
        MessageQuotedUiElementsReply::class,
        MessageQuoteInvoice::class,
        MessageRevoked::class,
        Messages::class,
        MessagesDehydratedHsm::class,
        MessagesEdit::class,
        MessageSendCount::class,
        MessagesHydratedFourRowTemplate::class,
        MessagesLinks::class,
        MessagesQuote::class,
        MessageStreamingSidecar::class,
        MessagesVcard::class,
        MessagesVcardsJid::class,
        MessageSystem::class,
        MessageSystemBlockContact::class,
        MessageSystemBusinessState::class,
        MessageSystemChatParticipant::class,
        MessageSystemDeviceChange::class,
        MessageSystemEphemeralSettingNotApplied::class,
        MessageSystemGroup::class,
        MessageSystemInitialPrivacyProvider::class,
        MessageSystemNumberChange::class,
        MessageSystemPhotoChange::class,
        MessageSystemValueChange::class,
        MessageText::class,
        MessageThumbnail::class,
        MessageThumbnails::class,
        MessageUiElements::class,
        MessageUiElementsReply::class,
        MessageViewOnceMedia::class,
        MessageGroupInvite::class,
        MessageLocation::class,
        MessageMedia::class,
        MessageMediaInteractiveAnnotation::class,
        MessageMediaInteractiveAnnotationVertex::class,
        MessageOrder::class,
        MessageOrphanedEdit::class,
        MessageProduct::class,
        MessageQuoted::class,
        MessageQuotedGroupInvite::class,
        MessageQuotedLocation::class,
        MessageQuotedMentions::class,
        MessageQuoteOrder::class,
        MessageQuotedProduct::class,
        MessageQuotedVcard::class,
        MessageTemplate::class,
        MessageTemplateButton::class,
        MessageTemplateQuoted::class,
        MissedCallLogParticipant::class,
        MissedCallLogs::class,
        MmsThumbnailMetadata::class,
        PayTransactions::class,
        QuickReplies::class,
        QuickReplyAttachments::class,
        QuickReplyKeywords::class,
        QuickReplyUsage::class,
        quoted_message_product::class,
        ReceiptDevice::class,
        ReceiptOrphaned::class,
        Receipts::class,
        ReceiptUser::class,
        Status::class,
        StatusList::class,
        UserDevice::class,
        UserDeviceInfo::class
    ], version = 1,
    exportSchema = false
)
abstract class MsgStore : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun chatListDao(): ChatListDao
    abstract fun frequentDao(): FrequentDao
    abstract fun groupParticipantDao(): GroupParticipantDao
    abstract fun groupParticipantsHistoryDao(): GroupParticipantsHistoryDao
    abstract fun mediaRefDao(): MediaRefDao
    abstract fun mediaStreamingSidecarDao(): MediaStreamingSidecarDao
    abstract fun messagesEditDao(): MessagesEditDao
    abstract fun messagesQuoteDao(): MessagesQuoteDao
    abstract fun messagesVcardDao(): MessagesVcardDao
    abstract fun messagesVcardsJidDao(): MessagesVcardsJidDao
    abstract fun messageThumbnailDao(): MessageThumbnailDao
    abstract fun receiptDao(): ReceiptDao
    abstract fun statusListDao(): StatusListDao


    companion object {
        @Volatile
        private var INSTANCE: MsgStore? = null

        fun getDatabase(context: Context): MsgStore {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MsgStore::class.java,
                    "msgstore"
                ).createFromAsset("msgstore")
                    // 2
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // 3
                            db.execSQL("INSERT INTO messages_fts(messages_fts) VALUES ('rebuild')")
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}