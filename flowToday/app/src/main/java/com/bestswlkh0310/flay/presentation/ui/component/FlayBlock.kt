package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FlayBar(
    height: Dp,
    width: Dp
) {
    Surface(
        modifier = Modifier
            .height(height)
            .width(width),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp
        ),
        color = MaterialTheme.colorScheme.tertiary
    ) {

    }
}