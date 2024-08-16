package com.github.lexleontiev.chatexample.data

import com.github.lexleontiev.chatexample.data.room.MessageDTO
import com.github.lexleontiev.chatexample.data.room.MessageDao
import com.github.lexleontiev.chatexample.data.room.fromMessage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalDataSource @Inject constructor(
    private val messageDao: MessageDao
) {

    // For debug purposes only
    companion object {
        var throwFailure = false
    }

    fun getMessages(): Flow<List<Message>> = messageDao.getAllMessages().map {
        if (throwFailure) {
            throwFailure = false
            throw RuntimeException("Something went wrong")
        }
        it.map(MessageDTO::toMessage)
    }

    suspend fun saveMessage(message: Message) {
        messageDao.insertMessage(message.fromMessage())
    }

    suspend fun saveMessages(messages: List<Message>) {
        messageDao.insertMessages(messages.map(Message::fromMessage))
    }

    suspend fun removeAllMessages() {
        messageDao.deleteAllMessages()
    }
}