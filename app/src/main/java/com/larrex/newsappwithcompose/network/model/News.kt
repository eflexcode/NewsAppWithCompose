package com.larrex.newsappwithcompose.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News  (

    var status: String? = null,


    var totalResult: Int = 0,


    var articles: List<Article>? = null
)