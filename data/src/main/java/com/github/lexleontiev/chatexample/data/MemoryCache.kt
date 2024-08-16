package com.github.lexleontiev.chatexample.data

import javax.inject.Inject


class MemoryCache @Inject constructor() {

    private var messages: List<Message> = listOf()

    fun getMessages(): List<Message> = messages

    fun saveMessage(message: Message) {
        messages = listOf(message) + message
    }

    fun saveMessages(newMessages: List<Message>) {
        messages = listOf()
        messages = messages + newMessages
    }

    fun removeAllMessages() {
        messages = listOf()
    }
}