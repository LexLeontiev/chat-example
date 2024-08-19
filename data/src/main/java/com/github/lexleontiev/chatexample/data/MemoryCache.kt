package com.github.lexleontiev.chatexample.data

import javax.inject.Inject


class MemoryCache @Inject constructor() {

    private var messages = mutableListOf<Message>()

    fun getMessages(): List<Message> = messages

    fun saveMessage(message: Message) {
        messages.add(message)
    }

    fun saveMessages(newMessages: List<Message>) {
        messages.clear()
        messages.addAll(newMessages)
    }

    fun removeAllMessages() {
        messages.clear()
    }
}
