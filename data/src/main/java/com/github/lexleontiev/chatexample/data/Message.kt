package com.github.lexleontiev.chatexample.data


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
            id: Int = 0,
            text: String? = null,
            timestamp: Long = System.currentTimeMillis()
        ): Message = Message(
            id = id,
            content = text ?: "Test message",
            timestamp = timestamp,
            isSentByUser = isSentByUser
        )
    }
}

