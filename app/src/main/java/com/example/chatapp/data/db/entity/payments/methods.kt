package com.example.chatapp.data.db.entity.payments

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

class methods (
    @PrimaryKey
    @NotNull @ColumnInfo(name = "credential_id")var credential_id: String,
    @ColumnInfo(name = "country")var country: String,
    @ColumnInfo(name = "readable_name")var readable_name: String,
    @ColumnInfo(name = "issuer_name")var issuer_name: String,
    @ColumnInfo(name = "type")var type: Int,
    @ColumnInfo(name = "subtype")var subtype: Int,
    @ColumnInfo(name = "creation_ts")var creation_ts: Int,
    @ColumnInfo(name = "updated_ts")var updated_ts: Int,
    @NotNull @ColumnInfo(name = "debit_mode")var debit_mode: Int,
    @NotNull @ColumnInfo(name = "credit_mode")var credit_mode: Int,
    @ColumnInfo(name = "balance_1000")var balance_1000: Int,
    @ColumnInfo(name = "balance_ts")var balance_ts: Int,
    @ColumnInfo(name = "country_data")var country_data: String,
    @ColumnInfo(name = "icon",typeAffinity = ColumnInfo.BLOB)var icon: ByteArray
)

/*
CREATE TABLE tmp_transactions (tmp_id String NOT NULL, tmp_metadata String, tmp_ts Int)", "CREATE UNIQUE INDEX IF NOT EXISTS message_payment_transactions_index ON tmp_transactions (tmp_id)", "DROP TABLE IF EXISTS contacts",

                "CREATE TABLE contacts (" +
                        "jid NOT NULL, " +
                        "country_data String, " +
                        "merchant Int, " +
                        "default_payment_type Int)");

*/


//emojidictionary.db
//CREATE TABLE emoji_search_tag (_id Int PRIMARY KEY AUTOINCREMENT NOT NULL, type Int NOT NULL, symbol String NOT NULL, tag String NOT NULL)


// CREATE TABLE message (_id Int PRIMARY KEY AUTOINCREMENT, chat_row_id Int NOT NULL, from_me Int NOT NULL, key_id String NOT NULL, sender_jid_row_id Int, status Int, broadcast Int, recipient_count Int, participant_hash String, origination_flags Int, origin Int, timestamp Int, received_timestamp Int, receipt_server_timestamp Int, message_type Int, text_data String, starred Int, lookup_tables Int, sort_id Int NOT NULL DEFAULT 0 )