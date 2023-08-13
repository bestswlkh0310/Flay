package com.bestswlkh0310.flay.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.bestswlkh0310.flay.domain.model.base.GraphDto
import com.bestswlkh0310.flay.presentation.ui.modifier.flayClickable
import java.lang.Float.max

@Composable
fun <T> FlayBarGraph (
    modifier: Modifier = Modifier,
    data: List<GraphDto<T>> = arrayListOf(),
    height: Dp,
    labelHeight: Dp = 60.dp,
    labels: List<Pair<String, Color>>?,
    callback: (GraphDto<T>) -> Unit,
) {
    val max = data.maxByOrNull { item -> max(item.y, item.y2?: -1f) }?: GraphDto("null", 0f, null, null)
    val b = if ((max.y == 0.0f && max.y2 == null) || (max.y2 != null && max.y2 == 0.0f)) 1.dp  else height / max(max.y, max.y2?: -1f)

    val density = LocalDensity.current

    var measuredHeightDp by remember { mutableStateOf(0.dp) }
    var measuredWidth by remember { mutableStateOf(0.dp) }

    var isSecond = true
    data.forEach {
        if (it.y2 == null) isSecond = false
    }

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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(labelHeight)
                .padding(start = 7.dp)
        ) {
            items(labels?: arrayListOf()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = it.second,
                        shape = RoundedCornerShape(2.dp),
                        modifier = Modifier
                            .width(22.dp)
                            .height(10.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(5.dp))
                    FlayText(text = it.first, fontSize = 13.sp)
                }
            }
        }

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
                    if (isSecond) {
                        Box {
                            FlayBar(
                                maxHeight = height,
                                maxWidth = measuredWidth / data.size,
                                height = it.y2!! * b,
                                width = 11.dp,
                                xText = "",
                            )
                        }
                    }
                    FlayBar(
                        maxHeight = height,
                        height = it.y * b,
                        width = 11.dp,
                        xText = it.x,
                        maxWidth = measuredWidth / data.size,
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                    ) { dp ->
                        measuredHeightDp = dp
                    }
                }
            }
        }
        Divider(color = MaterialTheme.colorScheme.background, modifier = Modifier.offset(y = -measuredHeightDp))
    }
}