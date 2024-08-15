package com.github.lexleontiev.chatexample.library.android


data class Message(
    val id: Int,
    val content: String,
    val timestamp: Long,
    val isSentByUser: Boolean
) {

    companion object {

        fun new(isSentByUser: Boolean, text: String): Message = Message(
            id = 0,
            content = text,
            timestamp = System.currentTimeMillis(),
            isSentByUser = isSentByUser
        )

        fun mock(
            isSentByUser: Boolean,
            text: String? = null,
            timestamp: Long = System.currentTimeMillis()
        ): Message = Message(
            id = 0,
            content = text ?: "Test message",
            timestamp = timestamp,
            isSentByUser = isSentByUser
        )
    }
}

