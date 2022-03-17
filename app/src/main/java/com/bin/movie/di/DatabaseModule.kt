package com.bin.movie.di

import android.app.Application
import com.bin.movie.data.local.BinDatabase
import com.bin.movie.data.local.MovieDao
import com.bin.movie.data.local.PopularDao
import com.bin.movie.data.local.UpComingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): BinDatabase {
        return BinDatabase.instance(application)
    }

    @Provides
    @Singleton
    fun provideMovieDao(binDatabase: BinDatabase): MovieDao {
        return binDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun providePopularDao(binDatabase: BinDatabase): PopularDao {
        return binDatabase.popularDao()
    }

    @Provides
    @Singleton
    fun provideUpComingDao(binDatabase: BinDatabase): UpComingDao {
        return binDatabase.upComingDao()
    }

}