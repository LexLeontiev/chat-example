package com.github.lexleontiev.chatexample.data

import android.content.Context
import androidx.room.Room
import com.github.lexleontiev.chatexample.data.room.AppDatabase
import com.github.lexleontiev.chatexample.data.room.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "chat_database"
        ).build()
    }

    @Provides
    fun provideMessageDao(database: AppDatabase): MessageDao {
        return database.messageDao()
    }

    @Provides
    fun provideChatRepository(
        localDataSource: LocalDataSource,
        memoryCache: MemoryCache
    ): ChatRepository {
        return ChatRepositoryImpl(localDataSource, memoryCache)
    }

}
