package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.flay.presentation.ui.theme.noToSansKr

@Composable
fun FlayText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    fontSize: TextUnit = 15.sp,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None

) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontFamily = noToSansKr,
        fontWeight = FontWeight.Normal,
        modifier = modifier,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
            textAlign = textAlign
        ),
        textDecoration = textDecoration
    )
}