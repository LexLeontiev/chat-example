package com.github.lexleontiev.chatexample.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.withContext


class ChatRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val memoryCache: MemoryCache,
    private val dispatcher: CoroutineDispatcher
) : ChatRepository {

    override fun getMessages(): Flow<Result<List<Message>>> {
        return flow {
            // load from fast memory first
            val cachedMessages = memoryCache.getMessages()
            if (cachedMessages.isNotEmpty()) {
                emit(Result.success(cachedMessages))
            }

            // load from persistent memory
            localDataSource.getMessages().collect { localMessages ->
                memoryCache.saveMessages(localMessages)
                emit(Result.success(localMessages))
            }

        }.retryWhen { cause, attempt ->
            emit(Result.failure(cause))
            delay(3000)
            attempt < 3
        }
    }

    override suspend fun sendMessage(message: Message) = withContext(dispatcher) {
        memoryCache.saveMessage(message)
        localDataSource.saveMessage(message)
    }

    override suspend fun removeAllMessages() = withContext(dispatcher) {
        memoryCache.removeAllMessages()
        localDataSource.removeAllMessages()
    }
}
