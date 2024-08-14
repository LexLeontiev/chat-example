package com.github.lexleontiev.chatexample.app.ui.chat

import com.github.lexleontiev.chatexample.library.Message


data class ChatData(
    val messages: List<Message>,
) {

    companion object {

        fun empty() = ChatData(
            messages = emptyList()
        )
    }
}