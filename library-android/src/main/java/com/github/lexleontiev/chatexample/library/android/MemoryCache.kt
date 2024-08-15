package com.github.lexleontiev.chatexample.library.android

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

    fun removeAllMessages() {
        messages.clear()
    }
}