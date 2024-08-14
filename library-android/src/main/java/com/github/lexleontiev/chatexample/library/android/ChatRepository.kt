package com.github.lexleontiev.chatexample.library.android

import com.github.lexleontiev.chatexample.library.Message
import kotlinx.coroutines.flow.Flow


interface ChatRepository {
    fun getMessages(): Flow<List<Message>>
    suspend fun sendMessage(message: Message)
    suspend fun removeAllMessages()
}