package com.github.lexleontiev.chatexample.feature.chat

import com.github.lexleontiev.chatexample.data.ChatRepository
import com.github.lexleontiev.chatexample.data.DataModule
import com.github.lexleontiev.chatexample.data.Message
import com.github.lexleontiev.chatexample.feature.chat.components.mockList
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
@Module
object TestModule {

    @Provides
    fun provideChatRepository() = object : ChatRepository {
        override fun getMessages(): Flow<Result<List<Message>>> {
            return flowOf(Result.success(mockList()))
        }

        override suspend fun sendMessage(message: Message) {
            // no-op
        }

        override suspend fun removeAllMessages() {
            // no-op
        }
    }
}