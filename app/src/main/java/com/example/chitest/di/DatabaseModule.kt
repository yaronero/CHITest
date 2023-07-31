package com.example.chitest.di

import android.content.Context
import androidx.room.Room
import com.example.chitest.data.database.AppDatabase
import com.example.chitest.data.database.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideToDoDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideToDoDao(database: AppDatabase): UsersDao {
        return database.usersDao()
    }
}