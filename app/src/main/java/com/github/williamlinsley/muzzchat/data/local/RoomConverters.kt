package com.github.williamlinsley.muzzchat.data.local

import androidx.room.TypeConverter
import com.github.williamlinsley.muzzchat.domain.model.Sender
import java.time.Instant

class RoomConverters {

    @TypeConverter
    fun fromInstant(instant: Instant?): Long? =
        instant?.toEpochMilli()

    @TypeConverter
    fun toInstant(value: Long?): Instant? =
        value?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun fromSender(sender: Sender?): String? =
        sender?.name

    @TypeConverter
    fun toSender(value: String?): Sender? =
        value?.let { Sender.valueOf(it) }
}
