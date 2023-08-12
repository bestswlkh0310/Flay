package com.bestswlkh0310.flay.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.flay.presentation.ui.modifier.drawColoredShadow
import com.bestswlkh0310.flay.presentation.ui.modifier.flayClickable
import com.bestswlkh0310.flay.presentation.ui.utils.TimeCalculator
import kotlinx.coroutines.delay

@Composable
fun FlayLazyColumnItem(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .wrapContentHeight()
            .drawColoredShadow(Color.Black, 0.04f, 15.dp, 4.dp, 1.5.dp, 0.0.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isSystemInDarkTheme()) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
                )
                .fillMaxWidth()
                .wrapContentHeight()
                .flayClickable(
                    rippleEnable = true,
                    rippleColor = lerp(
                        (MaterialTheme.colorScheme.background),
                        MaterialTheme.colorScheme.secondary,
                        0.2f
                    ),
                    onClick = onClick
                )
                .padding(horizontal = 9.dp)
        ) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun a () {
    FlayLazyColumnItem(

    ) {
        FlayText(
            text = "it.title",
            modifier = Modifier
                .padding(top = 35.dp, start = 13.dp),
            color = MaterialTheme.colorScheme.background
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 13.dp, bottom = 13.dp)
        ) {
            var deadline by remember { mutableStateOf(TimeCalculator.calculateRemainingTime("2023-10-10 10:10:10")) }
//            LaunchedEffect(TimeCalculator.calculateRemainingTime(it.deadline)) {
//                while (true) {
//                    delay(1000)
//                    deadline = TimeCalculator.calculateRemainingTime(it.deadline)
//                            Log.d("TAG", "${it.deadline} - StopWatch() called")
//                }
//            }
            val year = deadline.year
            val month = deadline.month
            if (year > 0) {
                FlayText(text = year.toString(), fontSize = 24.sp)
                FlayText(text = "년", modifier = Modifier.padding(end = 5.dp))
            }
            if (month > 0) {
                FlayText(text = month.toString(), fontSize = 24.sp)
                FlayText(text = "월", modifier = Modifier.padding(end = 5.dp))
            }
            if (deadline.day > 0) {
                FlayText(text = deadline.day.toString(), fontSize = 24.sp)
                FlayText(text = "일")
            }
            val text = "%02d:%02d:%02d".format(deadline.hour, deadline.minute, deadline.second)
            FlayText(text = text, fontSize = 24.sp)
        }
    }
}