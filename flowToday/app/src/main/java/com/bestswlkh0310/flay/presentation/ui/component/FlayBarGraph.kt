package com.bestswlkh0310.flay.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.bestswlkh0310.flay.domain.model.base.GraphDto
import com.bestswlkh0310.flay.presentation.ui.modifier.flayClickable

@Composable
fun <T> FlayBarGraph (
    modifier: Modifier = Modifier,
    data: List<GraphDto<T>> = arrayListOf(),
    height: Dp,
    callback: (GraphDto<T>) -> Unit
) {
    val max = data.maxByOrNull { item -> item.y }?: GraphDto("null", 0f, null)
    val b = if (max.y == 0.0f) 1.dp  else height / max.y

    val density = LocalDensity.current

    var measuredHeightDp by remember { mutableStateOf(0.dp) }
    var measuredWidth by remember { mutableStateOf(0.dp) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                val measuredWidthPx = coordinates.size.width
                measuredWidth = (measuredWidthPx / density.density).dp.value.dp
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            items(data) {
                Box(
                    modifier = Modifier
                        .weight(1f)
//                        .background(Color.Red)
                        .flayClickable(
                            rippleEnable = true
                        ) {
                            callback(it)
                        }
                ) {
                    FlayBar(
                        maxHeight = height,
                        height = it.y * b,
                        width = 11.dp,
                        xText = it.x,
                        maxWidth = measuredWidth / data.size
                    ) { dp ->
                        measuredHeightDp = dp
                    }
                }
            }
        }
        Divider(color = MaterialTheme.colorScheme.background, modifier = Modifier.offset(y = -measuredHeightDp))
    }
}