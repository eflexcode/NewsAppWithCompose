package com.larrex.newsappwithcompose.network.apiInterface

import com.larrex.newsappwithcompose.network.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String?,
        @Query("apiKey") apikey: String?
    ): Call<News?>?

    @GET("top-headlines")
    fun getNewsWithCategory(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("apiKey") apikey: String?
    ): Call<News?>?

    @GET("everything")
    fun searchNews(@QueryMap searchMap: Map<String?, String?>?): Call<News?>?
}