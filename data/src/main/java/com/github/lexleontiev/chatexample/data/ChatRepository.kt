package com.github.lexleontiev.chatexample.data

import kotlinx.coroutines.flow.Flow


interface ChatRepository {
    fun getMessages(): Flow<Result<List<Message>>>
    suspend fun sendMessage(message: Message)
    suspend fun removeAllMessages()
}