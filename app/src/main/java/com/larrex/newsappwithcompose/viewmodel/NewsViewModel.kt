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

    private val selectedChipText: MutableState<String> = mutableStateOf("All")
     val searchText: MutableState<String> = mutableStateOf("Jetpack compose")

    fun getSelectedChipText1(): String {
        return selectedChipText.value
    }

    fun setSelectedChipText(text: String) {
        selectedChipText.value = text
    }

    private val repository = NewsRepository()

    fun runNews(category: String): Flow<List<Article>> {
        return repository.runNews(category)
    }
    fun searchNews(): Flow<List<Article>> {
        return repository.searchNews(searchText.value)
    }

}

