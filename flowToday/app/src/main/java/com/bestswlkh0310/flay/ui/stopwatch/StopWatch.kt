package com.bestswlkh0310.flay.ui.stopwatch

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.model.StopWatchDto
import com.bestswlkh0310.flay.ui.theme.FlayTheme
import com.bestswlkh0310.flay.ui.utils.CalculateRemainingTime.calculateRemainingTime
import com.bestswlkh0310.flay.ui.FlayIconButton
import com.bestswlkh0310.flay.ui.FlayLazyColumn
import com.bestswlkh0310.flay.ui.FlayLazyColumnItem
import com.bestswlkh0310.flay.ui.FlayText
import com.bestswlkh0310.flay.ui.FlayTitle
import com.bestswlkh0310.flay.ui.FlayNavigationActions
import kotlinx.coroutines.delay

@Composable
fun StopWatch(
    routeAction: FlayNavigationActions? = null,
    lazyListState: LazyListState
) {
    val coroutineScope = rememberCoroutineScope()
    val testCase = listOf(
                StopWatchDto(
                    title = "지구 멸망",
                    deadline = "2026-09-21 21:00:00"
                ),
                StopWatchDto(
                    title = "생일",
                    deadline = "2024-01-25 21:00:00"
                ),
                StopWatchDto(
                    title = "영화보러 가는 날",
                    deadline = "2023-08-22 21:00:00"
                ),
                StopWatchDto(
                    title = "영화보러 가는 날",
                    deadline = "2023-08-22 21:00:00"
                ),
                StopWatchDto(
                    title = "영화보러 가는 날",
                    deadline = "2023-08-22 21:00:00"
                ),
                StopWatchDto(
                    title = "영화보러 가는 날",
                    deadline = "2023-08-22 21:00:00"
                ),
        )

    FlayLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        lazyListState = lazyListState
        ) {
        item { FlayTitle(text = "스톱워치") }
        item {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (-20).dp)
            ) {
                FlayIconButton(
                    iconId = R.drawable.ic_add,
                    contentDescription = "add",
                ) {
                    // TODO: callback  func
                }
            }
        }
        items(testCase) {
            FlayLazyColumnItem {
                FlayText(
                    text = it.title,
                    modifier = Modifier
                        .padding(top = 35.dp, start = 13.dp),
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 13.dp)
                ) {
                    var deadline by remember { mutableStateOf(calculateRemainingTime(it.deadline)) }
                    LaunchedEffect(calculateRemainingTime(it.deadline)) {
                        while (true) {
                            delay(1000)
                            deadline = calculateRemainingTime(it.deadline)
                            Log.d("TAG", "${it.deadline} - StopWatch() called")
                        }
                    }
                    val modi = Modifier.padding(bottom = 8.dp, end = 4.dp)
                    val modi2 = Modifier.padding(bottom = 0.dp)
                    val year = deadline.year
                    val month = deadline.month
                    if (year > 0) {
                        FlayText(text = year.toString(), fontSize = 24.sp, modifier = modi2)
                        FlayText(text = "년", modifier = modi)
                    }
                    if (month > 0) {
                        FlayText(text = month.toString(), fontSize = 24.sp, modifier = modi2)
                        FlayText(text = "월", modifier = modi)
                    }
                    if (deadline.day > 0) {
                        FlayText(text = deadline.day.toString(), fontSize = 24.sp, modifier = modi2)
                        FlayText(text = "일", modifier = modi)
                    }
                    val text = "%02d:%02d:%02d".format(deadline.hour, deadline.minute, deadline.second)
                    FlayText(text = text, fontSize = 24.sp, modifier = modi2)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    FlayTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StopWatch(lazyListState =  rememberLazyListState())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    FlayTheme(darkTheme = true) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StopWatch(lazyListState =  rememberLazyListState())
        }
    }
}

