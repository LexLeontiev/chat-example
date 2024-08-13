package com.github.lexleontiev.chatexample.library.android

import com.github.lexleontiev.chatexample.library.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow


class ChatRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
    private val memoryCache: MemoryCache
) : ChatRepository {

    override fun getMessages(): Flow<List<Message>> {
        return flow {
            val cachedMessages = memoryCache.getMessages()
            if (cachedMessages.isNotEmpty()) {
                emit(cachedMessages)
            }

            val localMessages = localDataSource.getMessages()
            emit(localMessages.first())

            networkDataSource.getMessages().collect { networkMessages ->
                localDataSource.saveMessages(networkMessages)
                memoryCache.saveMessages(networkMessages)
                emit(networkMessages)
            }
        }
    }

    override suspend fun sendMessage(message: Message) {
        localDataSource.saveMessage(message)
        memoryCache.saveMessage(message)
        networkDataSource.sendMessage(message)
    }
}