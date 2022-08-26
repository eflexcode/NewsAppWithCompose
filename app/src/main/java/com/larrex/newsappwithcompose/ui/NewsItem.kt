package com.larrex.newsappwithcompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.larrex.newsappwithcompose.R
import com.larrex.newsappwithcompose.network.model.Article
import com.larrex.newsappwithcompose.ui.theme.*

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

@Composable
fun NewsItem(article: Article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier.weight(2f)) {

            Text(
                text = article.title,
                color = textColor,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 5.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Text(
                    text = article.source.name,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.SemiBold,
                    color = subTextColor,
                    modifier = Modifier.padding(2.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.time),
                    contentDescription = null,
                    tint = subTextColor,
                    modifier = Modifier.padding(2.dp)
                )

                Text(
                    text = "12h ago",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.SemiBold,
                    color = subTextColor,
                    modifier = Modifier.padding(2.dp)
                )

            }

        }

        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(top = 10.dp, end = 5.dp, start = 5.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(25.dp))
                .size(100.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(article.urlToImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
            )


        }

    }
}