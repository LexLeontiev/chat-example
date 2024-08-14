package com.github.lexleontiev.chatexample.library.android

import com.github.lexleontiev.chatexample.library.Message
import javax.inject.Inject


class MemoryCache @Inject constructor() {

    private val messages: MutableList<Message> = mutableListOf()

    fun getMessages(): List<Message> = messages

    fun saveMessage(message: Message) {
        messages.add(message)
    }

    fun saveMessages(newMessages: List<Message>) {
        messages.addAll(newMessages)
    }
}