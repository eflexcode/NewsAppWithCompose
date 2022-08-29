package com.larrex.newsappwithcompose.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.larrex.newsappwithcompose.network.apiInterface.NewsState
import com.larrex.newsappwithcompose.network.model.Article
import com.larrex.newsappwithcompose.network.model.News
import com.larrex.newsappwithcompose.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsViewModel : ViewModel() {

    val selectedChipText: MutableState<String> = mutableStateOf("All")
    val success: MutableState<News?> = mutableStateOf(value = null)
    val failure: MutableState<String?> = mutableStateOf(value = "")

    private val repository = NewsRepository()

     fun getNews(
        category: String,
        context: Context
    ): Flow<NewsState<News?>> {

        return repository.getNews(category, context)
    }
    fun runNews(category: String, context: Context): Flow<List<Article>>  {
        return repository.runNews(category, context)
    }

}

