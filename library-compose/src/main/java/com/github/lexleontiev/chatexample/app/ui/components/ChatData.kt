package com.github.lexleontiev.chatexample.app.ui.components

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