package com.github.lexleontiev.chatexample.library.android

import com.github.lexleontiev.chatexample.library.android.room.MessageDTO
import com.github.lexleontiev.chatexample.library.android.room.MessageDao
import com.github.lexleontiev.chatexample.library.android.room.fromMessage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalDataSource @Inject constructor(
    private val messageDao: MessageDao
) {

    fun getMessages(): Flow<List<Message>> = messageDao.getAllMessages().map {
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