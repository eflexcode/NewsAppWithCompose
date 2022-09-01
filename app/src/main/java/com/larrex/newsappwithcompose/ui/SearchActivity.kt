package com.larrex.newsappwithcompose.ui

import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.larrex.newsappwithcompose.R
import com.larrex.newsappwithcompose.Util
import com.larrex.newsappwithcompose.ui.theme.searchBarColor
import com.larrex.newsappwithcompose.viewmodel.NewsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SearchBox(navController : NavController) {

    val focusRequester = remember { FocusRequester() }
    var value by remember { mutableStateOf(TextFieldValue("")) }

    val viewModel = hiltViewModel<NewsViewModel>()

    val searchedNewsItem by viewModel.searchNews().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .padding(top = 0.dp, bottom = 0.dp, end = 5.dp, start = 5.dp)
            .fillMaxSize()
    ) {

        LazyColumn() {

            item {

                Column() {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                      navController.popBackStack()
                            },
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.ic_cross),
                                contentDescription = null
                            )

                        }

                        TextField(
                            value = value,
                            onValueChange = { text->
                                doSearch(text.text,viewModel)
                                value = text
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .background(Util.searchBarBackground, RoundedCornerShape(10.dp))
                                .focusRequester(focusRequester),
                            textStyle = TextStyle(fontSize = 16.sp),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true,
                            placeholder = { Text(text = "Start typing") },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = null
                                )
                            },

                        )
                        LaunchedEffect(Unit) {
                            focusRequester.requestFocus()
                        }
                    }


                }
            }

            items(searchedNewsItem) {

                NewsItem(it, onClicked = {
                    val encodedUrl = URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                    navController.navigate("details/"+encodedUrl+"/"+it.title)

                })

            }

        }


    }

}

fun doSearch(it: String, viewModel: NewsViewModel) {

    val handler = Handler()
    viewModel.searchText.value = it

    handler.postDelayed({

        viewModel.searchNews()

    },2000)


}
