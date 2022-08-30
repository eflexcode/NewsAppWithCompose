package com.larrex.newsappwithcompose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.larrex.newsappwithcompose.ui.theme.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Util {

    companion object {

        const val Base_url = "https://newsapi.org/v2/"
        const val DATA_STORE_CATEGORY_KEY = "current_category"
        const val ApiKey = ""
        const val Home = "home"
        const val Search = "search"
        const val DetailsLink = "details/{url}/{title}"
        const val Details = "details"
        const val Url = "url"
        const val Title = "title"

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

        fun getTodayDate(): String{

            val cal = Calendar.getInstance()

            return "Today "+SimpleDateFormat("MMM-dd").format(cal.getTime())

        }

         val subTextColor
            @Composable
            get() = if (isSystemInDarkTheme())
                SubTextColorDark
            else
                SubTextColor

         val textColor
            @Composable
            get() = if (isSystemInDarkTheme())
                TextColorDark
            else
                TextColor

         val chipBackgroundColor
            @Composable
            get() = if (isSystemInDarkTheme())
                DarkChipBackground
            else
                LightChipBackground

         val chipBackgroundColorSelected
            @Composable
            get() = if (isSystemInDarkTheme())
                DarkChipSelectedBackground
            else
                LightChipSelectedBackground


         val chipTextColor
            @Composable
            get() = if (isSystemInDarkTheme())
                DarkChipText
            else
                LightChipText

         val chipTextColorSelected
            @Composable
            get() = if (isSystemInDarkTheme())
                DarkChipTextSelected
            else
                LightChipTextSelected

        val searchBarBackground
            @Composable
            get() = if (isSystemInDarkTheme())
                searchBarColorDark
            else
                searchBarColor

    }

}

