package com.larrex.newsappwithcompose.repository

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.larrex.newsappwithcompose.R
import com.larrex.newsappwithcompose.Util
import com.larrex.newsappwithcompose.network.apiInterface.ApiInterface
import com.larrex.newsappwithcompose.network.apiInterface.NewsState
import com.larrex.newsappwithcompose.network.apiInterface.Status
import com.larrex.newsappwithcompose.network.model.News
import com.larrex.newsappwithcompose.network.retrofit.NewsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class NewsRepository {

    val success: MutableState<News?> = mutableStateOf(value = null)
    val failure: MutableState<String?> = mutableStateOf(value = "")

    suspend fun getNews(category: String?, context: Context): Flow<NewsState<News?>> {

        val apiInterface: ApiInterface =
            NewsClient.getNewsClient()?.create(ApiInterface::class.java)!!

        return flow<NewsState<News?>> {
            if (category == null) {
                // get all
                val news = apiInterface.getNews(Locale.getDefault().country, Util.ApiKey).execute()

                if (news.code() == 200) {

                    emit(NewsState.success(news.body()))

                }else{
                    emit(NewsState.error(news.message()))
                }


            } else {
                // get by category

                val news = apiInterface.getNewsWithCategory(Locale.getDefault().country, category, Util.ApiKey).execute()

                if (news.code() == 200) {

                    emit(NewsState.success(news.body()))

                }else{
                    emit(NewsState.error(news.message()))
                }
            }

        }.flowOn(Dispatchers.IO)

    }

    fun runNews(category: String?, context: Context) {

    }

}