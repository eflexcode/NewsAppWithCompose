package com.larrex.newsappwithcompose.ui

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.larrex.newsappwithcompose.R
import com.larrex.newsappwithcompose.ui.theme.TextColor
import com.larrex.newsappwithcompose.ui.theme.TextColorDark
import com.larrex.newsappwithcompose.viewmodel.NewsViewModel

private const val TAG = "DetailsActivity"

private val textColor
    @Composable
    get() = if (isSystemInDarkTheme())
        TextColorDark
    else
        TextColor


@Composable
fun LoadDetails(url: String?, title: String?, context: Context, navController: NavController) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.padding(0.dp)) {

        TopAppBar(
            title = {
                if (title != null && url != null) {

                    Column {
                        Text(
                            text = title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = url,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = textColor, maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }
            },
            backgroundColor = MaterialTheme.colors.background,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cross),
                        contentDescription = null
                    )
                }

            }

        )

        AndroidView(factory = {
            WebView(context).apply {
                webViewClient = WebViewClient()
                if (url != null) {
                    loadUrl(url)
                }
            }
        })

    }


}