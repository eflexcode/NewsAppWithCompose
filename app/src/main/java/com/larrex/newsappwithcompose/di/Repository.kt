package com.larrex.newsappwithcompose.di

import com.larrex.newsappwithcompose.network.repository.NewsRepositoryImpl
import com.larrex.newsappwithcompose.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Repository {

    @Binds
    @Singleton
    abstract fun bindsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}