package com.bestswlkh0310.flay.presentation.ui.feature.stopwatch

import android.util.Log
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
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.presentation.ui.FlayNavigationActions
import com.bestswlkh0310.flay.presentation.ui.component.AddStopWatch
import com.bestswlkh0310.flay.presentation.ui.component.EditStopWatch
import com.bestswlkh0310.flay.presentation.ui.component.FlayBottomButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayIconButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumn
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumnItem
import com.bestswlkh0310.flay.presentation.ui.component.FlayText
import com.bestswlkh0310.flay.presentation.ui.component.FlayTitle
import com.bestswlkh0310.flay.presentation.ui.theme.FlayTheme
import com.bestswlkh0310.flay.presentation.ui.utils.TimeCalculator.calculateRemainingTime
import kotlinx.coroutines.delay

@Composable
fun StopWatchScreen(
    routeAction: FlayNavigationActions? = null,
    lazyListState: LazyListState,
    viewModel: StopWatchViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    var showAddStopWatchDialog by remember { mutableStateOf(false) }
    var showEditStopWatchDialog by remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsState()

    if (showAddStopWatchDialog) {
        AddStopWatch(title ="제목과 종료일 입력해주세요", primaryButton = {
            FlayBottomButton(onClick = {
                viewModel.addStopWatch()
                showAddStopWatchDialog = false
            }) {
                FlayText(text = "완료")
            }
        }, secondaryButton = {
            FlayBottomButton(onClick = {
                showAddStopWatchDialog = false
            }) {
                FlayText(text = "취소")
            }
        },
            onDismiss = { showAddStopWatchDialog = false }, viewModel = viewModel)
    }

    if (showEditStopWatchDialog) {
        EditStopWatch(title = "수정 및 삭제", primaryButton = {
            FlayBottomButton(onClick = {
                viewModel.updateStopWatchComplete()
                showEditStopWatchDialog = false
            }) {
                FlayText(text = "저장")
            }
        }, secondaryButton = {
            FlayBottomButton(onClick = {
                showEditStopWatchDialog = false
            }) {
                FlayText(text = "닫기")
            }
        },
            deleteButton = {
            FlayBottomButton(onClick = {
                Log.d("TAG", "ㅂㅈㄷㅈㅂㄷㅂㅈㄷㅂㅈ - StopWatchScreen() called")
            }) {
                FlayText(text = "삭제")
            }
        },
            onDismiss = { showEditStopWatchDialog = false }, viewModel = viewModel)
    }

    LaunchedEffect(true) {
        viewModel.loadStopWatchList()
    }

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
                    onClick = {
                        showAddStopWatchDialog = true
                    }
                )
            }
        }

        items(state.stopWatchList, key = { it.idx }) {
            FlayLazyColumnItem(
                onClick = {
                    showEditStopWatchDialog = true
                    viewModel.updateClickedStopWatch(it)
                }
            ) {
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
//                            Log.d("TAG", "${it.deadline} - StopWatch() called")
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
            StopWatchScreen(lazyListState =  rememberLazyListState())
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
            StopWatchScreen(lazyListState =  rememberLazyListState())
        }
    }
}

