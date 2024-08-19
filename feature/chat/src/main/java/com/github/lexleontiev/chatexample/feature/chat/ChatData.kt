package com.github.lexleontiev.chatexample.feature.chat

import com.github.lexleontiev.chatexample.data.Message


data class ChatData(
    val messages: List<Message>,
) {

    companion object {

        fun empty() = ChatData(
            messages = emptyList()
        )
    }
}
