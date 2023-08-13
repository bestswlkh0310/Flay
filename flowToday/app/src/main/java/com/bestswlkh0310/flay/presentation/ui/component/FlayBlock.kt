package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FlayBar(
    maxHeight: Dp,
    maxWidth: Dp,
    height: Dp,
    width: Dp,
    xText: String? = null,
    density: Density = LocalDensity.current,
    color: Color = MaterialTheme.colorScheme.tertiary,
    measuredTextCallback: (Dp) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(maxHeight + 30.dp)
            .width(maxWidth),
        verticalArrangement = Arrangement.Bottom
    ) {
        Surface(
            modifier = Modifier
                .height(height)
                .width(width),
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp
            ),
            color = color
        ) {}
        if (xText != null) {
            FlayText(text = xText, fontSize = 11.sp, modifier = Modifier.onGloballyPositioned { coordinates ->
                val measuredHeightPx = coordinates.size.height
                val measuredHeightDpValue = (measuredHeightPx / density.density).dp.value
                measuredTextCallback(measuredHeightDpValue.dp)
            })
        }
    }
}