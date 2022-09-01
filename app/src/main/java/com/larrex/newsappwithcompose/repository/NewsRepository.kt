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

interface NewsRepository {

    fun runNews(category: String): Flow<List<Article>>

    fun searchNews(keyword: String): Flow<List<Article>>

}