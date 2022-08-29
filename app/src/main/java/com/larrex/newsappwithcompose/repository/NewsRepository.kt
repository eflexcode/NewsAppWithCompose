package com.larrex.newsappwithcompose.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import com.larrex.newsappwithcompose.Util
import com.larrex.newsappwithcompose.network.apiInterface.ApiInterface
import com.larrex.newsappwithcompose.network.apiInterface.NewsState
import com.larrex.newsappwithcompose.network.apiInterface.Status
import com.larrex.newsappwithcompose.network.model.Article
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
    private val TAG = "NewsRepository"
    fun getNews(category: String, context: Context): Flow<NewsState<News?>> {

        val apiInterface: ApiInterface =
            NewsClient.getNewsClient().create(ApiInterface::class.java)

//         val news = apiInterface.getNews(Locale.getDefault().country, Util.ApiKey).enqueue(object : Callback<News>{
//             override fun onResponse(call: Call<News>, response: Response<News>) {
//                 Log.d(TAG, "onResponse: "+response.body())
//             }
//
//             override fun onFailure(call: Call<News>, t: Throwable) {
//             }
//
//         })

        return flow<NewsState<News?>> {
            if (category == "all") {
                // get all
                val news = apiInterface.getNews(Locale.getDefault().country, Util.ApiKey).execute()

                if (news.isSuccessful) {

                    emit(NewsState.success(news.body()))

                } else {

                    emit(NewsState.error(news.message()))
                }


            } else {
                // get by category

                val news = category.let {
                    apiInterface.getNewsWithCategory(
                        Locale.getDefault().country,
                        it, Util.ApiKey
                    ).execute()
                }

                if (news.isSuccessful) {

                    emit(NewsState.success(news.body()))

                } else {

                    emit(NewsState.error("" + news.code()))

                }
            }

        }.flowOn(Dispatchers.IO)

    }

    fun runNews(category: String, context: Context): Flow<List<Article>> {

        val apiInterface: ApiInterface =
            NewsClient.getNewsClient().create(ApiInterface::class.java)

        return flow<List<Article>> {
            if (category == "all") {
                // get all
                val news = apiInterface.getNews(Locale.getDefault().country, Util.ApiKey).execute()

                if (news.isSuccessful) {

                    if (news.body() != null){

                    val result: List<Article> = news.body()!!.articles

                    emit(result)
                }
            } else {

//                    emit(NewsState.error(news.message()))
            }

        } else {
            // get by category

            val news =
                apiInterface.getNewsWithCategory(Locale.getDefault().country, category, Util.ApiKey)
                    .execute()


            if (news.isSuccessful) {

                if (news.body() != null){

                    val result: List<Article> = news.body()!!.articles

                    emit(result)
                }
            } else {


            }
        }
    }.flowOn(Dispatchers.IO)


}

}