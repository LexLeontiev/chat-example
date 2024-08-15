package com.github.lexleontiev.chatexample.data

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay


class NetworkDataSource @Inject constructor() {

    fun getMessages(): Flow<List<Message>> = flow {
        repeat (5) {
            delay(5000L)
            val receivedMessage = Message.new(
                isSentByUser = false,
                text = "Hi, what's new?"
            )
            emit(listOf(receivedMessage))
        }
    }

    suspend fun sendMessage(message: Message) {
        // emulate network delay
        delay(1000L)
    }
}
