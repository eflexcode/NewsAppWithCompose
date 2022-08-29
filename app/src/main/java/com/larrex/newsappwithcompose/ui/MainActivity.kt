package com.larrex.newsappwithcompose.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.larrex.newsappwithcompose.CategoryChip
import com.larrex.newsappwithcompose.R
import com.larrex.newsappwithcompose.Util
import com.larrex.newsappwithcompose.network.apiInterface.NewsState
import com.larrex.newsappwithcompose.network.apiInterface.Status
import com.larrex.newsappwithcompose.network.model.Article
import com.larrex.newsappwithcompose.network.model.News
import com.larrex.newsappwithcompose.ui.theme.*
import com.larrex.newsappwithcompose.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            NewsAppWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BaseUi(applicationContext)
                }
            }
        }
    }
}

private val subTextColor
    @Composable
    get() = if (isSystemInDarkTheme())
        SubTextColorDark
    else
        SubTextColor

private val textColor
    @Composable
    get() = if (isSystemInDarkTheme())
        TextColorDark
    else
        TextColor

private val chipBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme())
        DarkChipBackground
    else
        LightChipBackground

private val chipBackgroundColorSelected
    @Composable
    get() = if (isSystemInDarkTheme())
        DarkChipSelectedBackground
    else
        LightChipSelectedBackground

@Composable
fun BaseUi(context: Context) {

    val listOfChips = listOf("All", "Business", "Entertainment" ,"Science", "Technology", "Sports")
    val viewModel = viewModel<NewsViewModel>()

    val newsItem by viewModel.runNews(viewModel.selectedChipText.value.toLowerCase(), context)
        .collectAsState(initial = emptyList())

    val TAG = "MainActivity"

//    val articleList =  newsItem?.data?.articles!!

    Box(
        modifier = Modifier
            .padding(top = 0.dp, bottom = 0.dp, end = 10.dp, start = 10.dp)
            .fillMaxSize()

    ) {
        Log.d(TAG, "BaseUi: " + newsItem)

        LazyColumn() {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column(modifier = Modifier.weight(2f)) {
                        Text(
                            text = "Headline.",
                            modifier = Modifier.padding(end = 16.dp, top = 10.dp),
                            color = Green,
                            fontSize = 25.sp,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Today February 23",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.SemiBold,
                            color = subTextColor
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(0.3f)) {

                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null
                        )

                    }
                }
                if (newsItem.size > 0) newsItem[0].title else ""
                Card(
                    elevation = 10.dp,
                    modifier = Modifier
                        .padding(top = 30.dp, end = 5.dp, start = 5.dp)
                        .clip(RoundedCornerShape(25.dp))
                ) {

                    Image(
                        painter = rememberImagePainter(
                            if (newsItem.size > 0) newsItem[0].urlToImage else "",
                            builder = {
                                placeholder(R.color.gray)
                            }),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(25.dp),
                            )
                            .fillMaxWidth()
                            .size(250.dp)
                    )

                        CategoryChip(
                            "Trending",
                            chipSelected = true,
                            onChipSelected = {


                            },modifier = Modifier.padding(top = 20.dp, end = 5.dp, start = 20.dp).clip(RoundedCornerShape(30.dp)))


//                    Card(
//                        modifier = Modifier
//                            .padding()
//                            .clip(RoundedCornerShape(50.dp))
//                    ) {
//
//
//                    }
                }

                Text(
                    text = if (newsItem.size > 0) newsItem[0].title else "",
                    modifier = Modifier.padding(end = 16.dp, top = 15.dp, start = 16.dp),
                    color = textColor,
                    fontSize = 23.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Text(
                        text = "World News",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.SemiBold,
                        color = subTextColor,
                        modifier = Modifier.padding(2.dp)
                    )

//                    Icon(
//                        painter = painterResource(id = R.drawable.time),
//                        contentDescription = null,
//                        tint = subTextColor,
//                        modifier = Modifier.padding(2.dp)
//                    )

                    Text(
                        text = if (newsItem.size > 0) Util.getTimePassed(newsItem[0].publishedAt) else "",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.SemiBold,
                        color = subTextColor,
                        modifier = Modifier.padding(2.dp)
                    )

                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)

                ) {
                    items(listOfChips) {

                        CategoryChip(
                            it,
                            chipSelected = it == viewModel.selectedChipText.value,
                            onChipSelected = {

                                viewModel.selectedChipText.value = it

                            }, modifier = Modifier
                                .padding(4.dp)
                                .clip(RoundedCornerShape(30.dp)))
                    }

                }

            }

            items(newsItem) {

                NewsItem(it)

            }
        }


    }


}



@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun DefaultPreview() {
    NewsAppWithComposeTheme {
//        BaseUi()
    }
}