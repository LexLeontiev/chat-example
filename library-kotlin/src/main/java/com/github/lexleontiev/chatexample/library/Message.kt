package com.github.lexleontiev.chatexample.library

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class Message(
    val content: String,
    val timestamp: Long,
    val isSentByUser: Boolean
) {

    companion object {

        fun new(isSentByUser: Boolean, text: String): Message = Message(
            content = text,
            timestamp = System.currentTimeMillis(),
            isSentByUser = isSentByUser
        )

        fun mock(isSentByUser: Boolean, text: String? = null): Message = Message(
            content = text ?: "Test message",
            timestamp = System.currentTimeMillis(),
            isSentByUser = isSentByUser
        )

        fun mockList(): List<Message> = listOf(
            mock(true, "Hello"),
            mock(true, "How are you?"),
            mock(false, "Hey hey, I'm fine :)"),
            mock(false, "How you doing?"),
            mock(true, "I'm alright as well, thank you")
        )

        fun formatDate(timestamp: Long): String {
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
    }
}

