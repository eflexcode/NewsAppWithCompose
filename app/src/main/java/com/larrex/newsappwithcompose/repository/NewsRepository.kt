package com.larrex.newsappwithcompose.repository

import com.larrex.newsappwithcompose.Util
import com.larrex.newsappwithcompose.network.apiInterface.ApiInterface
import com.larrex.newsappwithcompose.network.model.News
import com.larrex.newsappwithcompose.network.retrofit.NewsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class NewsRepository {

    fun getNews(category: String?) {

        val apiInterface: ApiInterface =
            NewsClient.getNewsClient()?.create(ApiInterface::class.java)!!

        if (category == null) {
            // get all
            apiInterface.getNews(Locale.getDefault().country, Util.ApiKey)
                .enqueue(object : Callback<News> {
                    override fun onResponse(call: Call<News>, response: Response<News>) {

                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {
                    }

                })


        } else {
            // get by category

            apiInterface.getNewsWithCategory(Locale.getDefault().country, category, Util.ApiKey)
                .enqueue(object : Callback<News> {
                    override fun onResponse(call: Call<News>, response: Response<News>) {

                        if(response.code() == 200){

                        }else{

                        }

                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {
                    }

                })
        }

    }

}