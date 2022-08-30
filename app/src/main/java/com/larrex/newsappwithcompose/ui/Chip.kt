package com.larrex.newsappwithcompose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larrex.newsappwithcompose.ui.theme.*


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


private val chipTextColor
    @Composable
    get() = if (isSystemInDarkTheme())
        DarkChipText
    else
        LightChipText

private val chipTextColorSelected
    @Composable
    get() = if (isSystemInDarkTheme())
        DarkChipTextSelected
    else
        LightChipTextSelected

@Composable
fun CategoryChip(
    chipText: String,
    chipSelected: Boolean = false,
    onChipSelected: (String) -> Unit,
    modifier: Modifier =
        Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(30.dp))
) {

    Surface(
        modifier = modifier,
        elevation = 10.dp,
        color = if (chipSelected) chipBackgroundColorSelected else chipBackgroundColor,

        ) {

        Row(modifier = Modifier
            .toggleable(value = chipSelected, onValueChange = {
                onChipSelected(chipText)
            })
            .padding(top = 10.dp, end = 20.dp, start = 20.dp, bottom = 10.dp)) {

            Text(
                text = chipText,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                color = if (chipSelected) chipTextColorSelected else chipTextColor
            )

        }

    }


}