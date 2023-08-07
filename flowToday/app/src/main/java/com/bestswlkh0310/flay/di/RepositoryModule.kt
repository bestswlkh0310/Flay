package com.bestswlkh0310.flay.di

import com.bestswlkh0310.flay.data.repository.StopWatchRepository
import com.bestswlkh0310.flay.domain.repository.StopWatchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideStopWatchRepository(stopWatchRepositoryImpl: StopWatchRepositoryImpl): StopWatchRepository
}