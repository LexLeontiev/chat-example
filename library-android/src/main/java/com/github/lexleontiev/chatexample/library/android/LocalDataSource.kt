package com.github.lexleontiev.chatexample.library.android

import com.github.lexleontiev.chatexample.library.Message
import kotlinx.coroutines.flow.Flow


// Todo database impl
class LocalDataSource {

    fun getMessages(): Flow<List<Message>> {
        return TODO("implement")
    }

    suspend fun saveMessage(message: Message) {
        TODO("implement")
    }

    suspend fun saveMessages(messages: List<Message>) {
        TODO("implement")
    }
}