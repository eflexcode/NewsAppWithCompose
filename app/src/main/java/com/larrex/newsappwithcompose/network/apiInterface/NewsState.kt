package com.larrex.newsappwithcompose.network.apiInterface

data class NewsState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T): NewsState<T> {

            return NewsState(Status.SUCCESS, data, null)

        }

        fun <T> loading(): NewsState<T> {

            return NewsState(Status.LOADING, null, null)

        }

        fun <T> error(message: String?): NewsState<T> {

            return NewsState(Status.ERROR, null, message)

        }

    }

}