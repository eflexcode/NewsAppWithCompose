package com.larrex.newsappwithcompose

import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Util {

    companion object {
        const val Base_url = "https://newsapi.org/v2/"
        const val DATA_STORE_CATEGORY_KEY = "current_category"
        const val ApiKey = "2f3a84ce7aa542d09c74f7e58cc2375c"

        fun getTimePassed(newsTime: String): String {

            val prettyTime = PrettyTime(Locale.getDefault().country)

            var theTime: String? = null

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

            try {
                val date = simpleDateFormat.parse(newsTime)
                theTime = prettyTime.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return "• $theTime"
        }

    }

}

