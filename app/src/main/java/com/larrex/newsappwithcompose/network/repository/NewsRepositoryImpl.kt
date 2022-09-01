package com.larrex.newsappwithcompose.network.repository

import com.larrex.newsappwithcompose.Util
import com.larrex.newsappwithcompose.Util.Companion.ApiKey
import com.larrex.newsappwithcompose.network.apiInterface.ApiInterface
import com.larrex.newsappwithcompose.network.model.Article
import com.larrex.newsappwithcompose.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private var apiInterface: ApiInterface) : NewsRepository {

    override fun runNews(category: String): Flow<List<Article>> {


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

    override fun searchNews(keyword: String): Flow<List<Article>> {

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