package com.bin.movie.di

import android.app.Application
import com.bin.movie.data.remote.ApiClient
import com.bin.movie.data.remote.ApiService
import com.bin.movie.data.remote.NetworkStateManager
import com.bin.movie.data.remote.NetworkStateManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiClient.instance
    }

    @Provides
    @Singleton
    fun provideNetworkStateManager(application: Application): NetworkStateManager {
        return NetworkStateManagerImpl(application)
    }
}