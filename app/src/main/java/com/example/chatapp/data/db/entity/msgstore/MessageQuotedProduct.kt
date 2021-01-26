package com.example.chatapp.data.db.entity.msgstore
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_quoted_product")
class MessageQuotedProduct(
    @PrimaryKey
    @ColumnInfo(name = "message_row_id")var message_row_id: Int,
    @ColumnInfo(name = "business_owner_jid")var business_owner_jid: Int,
    @ColumnInfo(name = "product_id")var product_id: String,
    @ColumnInfo(name = "title")var title: String,
    @ColumnInfo(name = "description")var description: String,
    @ColumnInfo(name = "currency_code")var currency_code: String,
    @ColumnInfo(name = "amount_1000")var amount_1000: Int,
    @ColumnInfo(name = "retailer_id")var retailer_id: String,
    @ColumnInfo(name = "url")var url: String,
    @ColumnInfo(name = "product_image_count")var product_image_count: Int
)