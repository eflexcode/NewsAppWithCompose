package com.larrex.newsappwithcompose.repository

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.larrex.newsappwithcompose.Util
import com.larrex.newsappwithcompose.Util.Companion.ApiKey
import com.larrex.newsappwithcompose.network.apiInterface.ApiInterface
import com.larrex.newsappwithcompose.network.model.Article
import com.larrex.newsappwithcompose.network.model.News
import com.larrex.newsappwithcompose.network.retrofit.NewsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

class NewsRepository {

    val success: MutableState<News?> = mutableStateOf(value = null)
    val failure: MutableState<String?> = mutableStateOf(value = "")
    private val TAG = "NewsRepository"

    fun runNews(category: String): Flow<List<Article>> {

        val apiInterface: ApiInterface =
            NewsClient.getNewsClient().create(ApiInterface::class.java)

        return flow<List<Article>> {
            if (category == "all") {
                // get all
                val news = apiInterface.getNews(Locale.getDefault().country, Util.ApiKey).execute()

                if (news.isSuccessful) {

                    if (news.body() != null) {

                        val result: List<Article> = news.body()!!.articles

                        emit(result)
                    }
                } else {

                }

            } else {
                // get by category

                val news =
                    apiInterface.getNewsWithCategory(
                        Locale.getDefault().country,
                        category,
                        Util.ApiKey
                    )
                        .execute()


                if (news.isSuccessful) {

                    if (news.body() != null) {

                        val result: List<Article> = news.body()!!.articles

                        emit(result)
                    }
                } else {


                }
            }
        }.flowOn(Dispatchers.IO)


    }

    fun searchNews(keyword: String): Flow<List<Article>> {

        val apiInterface: ApiInterface =
            NewsClient.getNewsClient().create(ApiInterface::class.java)

        return flow<List<Article>> {

            val map: MutableMap<String, String> = HashMap()

            map["language"] = Locale.getDefault().language
            map["q"] = keyword
            map["sortBy"] = "publishedAt"
            map["apiKey"] = ApiKey

            val news = apiInterface.searchNews(map).execute()

            if (news.isSuccessful) {

                if (news.body() != null) {

                    val result: List<Article> = news.body()!!.articles

                    emit(result)
                }
            } else {

            }


        }.flowOn(Dispatchers.IO)


    }

}