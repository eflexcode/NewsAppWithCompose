package com.larrex.newsappwithcompose.ui

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larrex.newsappwithcompose.R
import com.larrex.newsappwithcompose.ui.theme.searchBarColor

@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun SearchBox() {

    val focus = remember {
        FocusRequester()
    }
    var value by remember { mutableStateOf(TextFieldValue("")) }

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
                                doSearch(text.text)
                                value = text
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .background(searchBarColor, RoundedCornerShape(10.dp)),
                            textStyle = TextStyle(fontSize = 16.sp),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            maxLines = 1,
                            placeholder = { Text(text = "Type topic and click ok in keyboard") },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = null
                                )
                            },

                        )

                    }


                }
            }

        }


    }

}

fun doSearch(it: String) {

}
