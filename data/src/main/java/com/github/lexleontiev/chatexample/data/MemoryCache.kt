package com.github.lexleontiev.chatexample.data

import javax.inject.Inject


class MemoryCache @Inject constructor() {

    private val messages: MutableList<Message> = mutableListOf()

    fun getMessages(): List<Message> = messages

    fun saveMessage(message: Message) {
        messages.add(0, message)
    }

    fun saveMessages(newMessages: List<Message>) {
        messages.clear()
        messages.addAll(newMessages)
    }

    fun removeAllMessages() {
        messages.clear()
    }
}