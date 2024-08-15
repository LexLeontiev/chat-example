package com.github.lexleontiev.chatexample.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ChatRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val memoryCache: MemoryCache
) : ChatRepository {

    override fun getMessages(): Flow<List<Message>> {
        return flow {
            // load from fast memory first
            val cachedMessages = memoryCache.getMessages()
            if (cachedMessages.isNotEmpty()) {
                emit(cachedMessages)
            }

            // load from persistent memory
            localDataSource.getMessages().collect { localMessages ->
                memoryCache.saveMessages(localMessages)
                emit(localMessages)
            }
        }
    }

    override suspend fun sendMessage(message: Message) {
        memoryCache.saveMessage(message)
        localDataSource.saveMessage(message)
    }

    override suspend fun removeAllMessages() {
        memoryCache.removeAllMessages()
        localDataSource.removeAllMessages()
    }
}