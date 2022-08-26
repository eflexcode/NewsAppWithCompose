package com.larrex.newsappwithcompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    val selectedChipText  :MutableState<String> = mutableStateOf("All")

}