package com.larrex.newsappwithcompose.network.model

import com.google.gson.annotations.SerializedName

data class Article(
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null,

    var source: Source? = null
)
