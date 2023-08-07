package com.bestswlkh0310.flay.di

import android.content.Context
import androidx.room.Room
import com.bestswlkh0310.flay.data.Tables
import com.bestswlkh0310.flay.data.dao.StopWatchDao
import com.bestswlkh0310.flay.data.database.FlayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideFlayDatabase(
        @ApplicationContext context: Context,
    ): FlayDatabase = Room
        .databaseBuilder(
            context,
            FlayDatabase::class.java,
            Tables.DATABASE
        )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideStopWatchDao(
        flayDatabase: FlayDatabase
    ): StopWatchDao = flayDatabase.stopWatchDao()
}
