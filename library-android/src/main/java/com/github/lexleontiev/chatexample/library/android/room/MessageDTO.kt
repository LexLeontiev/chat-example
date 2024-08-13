package com.github.lexleontiev.chatexample.library.android.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.lexleontiev.chatexample.library.Message


@Entity(tableName = "messages")
data class MessageDTO(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    val timestamp: Long,
    val isSentByUser: Boolean
) {

    fun toMessage() = Message(
        id = id,
        content = content,
        timestamp = timestamp,
        isSentByUser = isSentByUser
    )
}

fun Message.fromMessage() = MessageDTO(
    id = id,
    content = content,
    timestamp = timestamp,
    isSentByUser = isSentByUser
)
