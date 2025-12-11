package com.github.williamlinsley.muzzchat.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.williamlinsley.muzzchat.domain.model.Sender
import java.time.Instant

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val sender: Sender,
    val timestamp: Instant
)
