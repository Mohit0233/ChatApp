package com.example.chatapp.data.db.entity

import androidx.room.*
import com.example.chatapp.data.db.entity.msgstore.Messages

//content, fts_jid, fts_namespace
@Fts4(contentEntity = Messages::class)
@Entity(tableName = "messages_fts")
data class MessagesFTS( // contains a record of group conversation
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "media_url") val mediaUrl: String,
    //Todo person name cannot find by it because table messages doesn't contain it
)

//SELECT count(*) AS count FROM message_ftsv2 WHERE message_ftsv2 MATCH ?
//AnonymousClass008.A1Y(A0S40, "(SELECT message_row_id, link_index FROM message_link WHERE message_row_id IN ( SELECT docid FROM message_ftsv2, message_view AS message WHERE message_ftsv2 MATCH ? AND message_ftsv2.docid = message._id)) links", " WHERE ", "message.", "_id");
//AnonymousClass008.A1Y(A0S60, "_id", " IN ", "( \n   SELECT docid \n   FROM message_ftsv2 AS messages_fts, available_message_view AS message \n   WHERE messages_fts.content MATCH ? AND messages_fts.docid = message._id\n   AND message.starred = 1   AND (message_type != '7'))\n", " ORDER BY ");
//A18 = AnonymousClass008.A0Q(A0S63, "_id", " IN ", "(SELECT docid FROM message_ftsv2 AS messages_fts, message_view AS message WHERE messages_fts.content MATCH ? AND messages_fts.docid = message._id AND message.starred = 1 AND (message_type != '7'))", " ORDER BY _id DESC");
//java.lang.String r5 = "SELECT fts_jid, count(*) AS count FROM message_ftsv2 WHERE message_ftsv2 MATCH ? GROUP BY fts_jid"

data class MessageWithMatchInfo(
    @Embedded
    val messages: Messages,
    @ColumnInfo(name = "matchInfo")
    val matchInfo: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageWithMatchInfo

        if (messages != other.messages) return false
        if (!matchInfo.contentEquals(other.matchInfo)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = messages.hashCode()
        result = 31 * result + matchInfo.contentHashCode()
        return result
    }
}