package com.example.chatapp.data.db.entity.msgstore
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_template_button")
class MessageTemplateButton (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @ColumnInfo(name = "message_row_id")var message_row_id: Int,
    @NotNull @ColumnInfo(name = "text_data")var text_data: String,
    @ColumnInfo(name = "extra_data")var extra_data: String,
    @ColumnInfo(name = "button_type")var button_type: Int,
    @ColumnInfo(name = "used")var used: Int,
    @ColumnInfo(name = "selected_index")var selected_index: Int
        )