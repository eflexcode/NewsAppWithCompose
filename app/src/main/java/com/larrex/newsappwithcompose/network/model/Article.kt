package com.larrex.newsappwithcompose.network.model

import com.google.gson.annotations.SerializedName

data class Article(
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String,

    var source: Source
)
