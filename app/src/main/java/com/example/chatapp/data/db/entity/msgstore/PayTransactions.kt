package com.example.chatapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pay_transactions")
class PayTransactions (
    @ColumnInfo(name = "key_remote_jid")var key_remote_jid: Int,
    @ColumnInfo(name = "key_from_me")var key_from_me: Int,
    @ColumnInfo(name = "key_id")var key_id: Int,
    @PrimaryKey
    @ColumnInfo(name = "id")var id: Int,
    @ColumnInfo(name = "status")var status: Int,
    @ColumnInfo(name = "error_code")var error_code: Int,
    @ColumnInfo(name = "sender")var sender: Int,
    @ColumnInfo(name = "receiver")var receiver: Int,
    @ColumnInfo(name = "type")var type: Int,
    @ColumnInfo(name = "currency")var currency: Int,
    @ColumnInfo(name = "amount_1000")var amount_1000: Int,
    @ColumnInfo(name = "credential_id")var credential_id: Int,
    @ColumnInfo(name = "methods")var methods: Int,
    @ColumnInfo(name = "bank_transaction_id")var bank_transaction_id: Int,
    @ColumnInfo(name = "metadata")var metadata: Int,
    @ColumnInfo(name = "init_timestamp")var init_timestamp: Int,
)