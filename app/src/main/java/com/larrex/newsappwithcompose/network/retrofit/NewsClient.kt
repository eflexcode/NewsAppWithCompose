package com.larrex.newsappwithcompose.network.retrofit

import com.larrex.newsappwithcompose.Util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsClient {


    companion object{

//        private  var retrofit : Retrofit = null

        fun getNewsClient(): Retrofit {


            return Retrofit.Builder()
                .baseUrl(Util.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}