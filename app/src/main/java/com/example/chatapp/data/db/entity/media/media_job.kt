package com.example.chatapp.data.db.entity.media

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class media_job (
    @PrimaryKey
    @ColumnInfo(name = "_id")var _id: Int,
    @ColumnInfo(name = "uuid")var uuid: String,
    @ColumnInfo(name = "job_type")var job_type: Int,
    @ColumnInfo(name = "create_time")var create_time: Long,
    @ColumnInfo(name = "transfer_start_time")var transfer_start_time: Long,
    @ColumnInfo(name = "last_update_time")var last_update_time: Long,
    @ColumnInfo(name = "user_initiated_attempt_count")var user_initiated_attempt_count: Int,
    @ColumnInfo(name = "overall_cumulative_time")var overall_cumulative_time: Long,
    @ColumnInfo(name = "overall_cumulative_user_visible_time")var overall_cumulative_user_visible_time: Long,
    @ColumnInfo(name = "streaming_playback_count")var streaming_playback_count: Int,
    @ColumnInfo(name = "media_key_reuse_type")var media_key_reuse_type: Int,
    @ColumnInfo(name = "doodle_id")var doodle_id: String,
    @ColumnInfo(name = "transferred_bytes")var transferred_bytes: Int,
    )