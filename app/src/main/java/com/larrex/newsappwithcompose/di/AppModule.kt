package com.larrex.newsappwithcompose.di

import com.larrex.newsappwithcompose.Util
import com.larrex.newsappwithcompose.network.apiInterface.ApiInterface
import com.larrex.newsappwithcompose.network.repository.NewsRepositoryImpl
import com.larrex.newsappwithcompose.network.retrofit.NewsClient
import com.larrex.newsappwithcompose.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNewClient(): ApiInterface {

        return Retrofit.Builder()
            .baseUrl(Util.Base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }


}