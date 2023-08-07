package com.bestswlkh0310.flay.presentation.ui.feature.stopwatch

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.presentation.ui.FlayNavigationActions
import com.bestswlkh0310.flay.presentation.ui.component.AddStopWatch
import com.bestswlkh0310.flay.presentation.ui.component.EditStopWatch
import com.bestswlkh0310.flay.presentation.ui.component.FlayButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayDialog
import com.bestswlkh0310.flay.presentation.ui.component.FlayIconButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumn
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumnItem
import com.bestswlkh0310.flay.presentation.ui.component.FlayText
import com.bestswlkh0310.flay.presentation.ui.component.FlayTitle
import com.bestswlkh0310.flay.presentation.ui.theme.FlayTheme
import com.bestswlkh0310.flay.presentation.ui.utils.TimeCalculator.calculateRemainingTime
import com.bestswlkh0310.flay.presentation.ui.utils.TimeCalculator.isTimeOver
import kotlinx.coroutines.delay

@Composable
fun StopWatchScreen(
    routeAction: FlayNavigationActions? = null,
    lazyListState: LazyListState,
    viewModel: StopWatchViewModel = hiltViewModel()
) {
    var showAddStopWatchDialog by remember { mutableStateOf(false) }
    var showEditStopWatchDialog by remember { mutableStateOf(false) }
    var showDeleteWatchDialog by remember { mutableStateOf(false) }

    val value by viewModel.state.collectAsState()
    val state by viewModel.sideEffect.collectAsState()

    when (state) {
        SideEffect.WrongTitle -> Toast.makeText(LocalContext.current, "제목을 제대로 입력해 주세요", Toast.LENGTH_SHORT).show()
        SideEffect.WrongDeadline -> Toast.makeText(LocalContext.current, "미래의 시간을 입력해 주세요", Toast.LENGTH_SHORT).show()
        SideEffect.AddStopWatchComplete -> showAddStopWatchDialog = false
        SideEffect.EditStopWatchComplete -> showEditStopWatchDialog = false
        else -> {}
    }
    viewModel.noneEffect()

    if (showAddStopWatchDialog) {
        AddStopWatch(title ="제목과 종료일 입력해주세요", primaryButton = {
            FlayButton(onClick = {
                viewModel.addStopWatch()
            }) {
                FlayText(text = "완료")
            }
        }, secondaryButton = {
            FlayButton(onClick = {
                showAddStopWatchDialog = false
            }) {
                FlayText(text = "취소")
            }
        },
            onDismiss = { showAddStopWatchDialog = false }, viewModel = viewModel)
    }

    if (showEditStopWatchDialog) {
        EditStopWatch(title = "수정 및 삭제", primaryButton = {
            FlayButton(onClick = {
                viewModel.updateStopWatchComplete()
            }) {
                FlayText(text = "저장")
            }
        }, secondaryButton = {
            FlayButton(onClick = {
                showEditStopWatchDialog = false
            }) {
                FlayText(text = "닫기")
            }
        },
            deleteButton = {
            FlayButton(onClick = {
                showDeleteWatchDialog = true
            }) {
                FlayText(text = "삭제")
            }
        },
            onDismiss = { showEditStopWatchDialog = false }, viewModel = viewModel)
    }

    if (showDeleteWatchDialog) {
        FlayDialog(title = "정말 삭제하시겠습니까?",
            primaryButton = {
                FlayButton(onClick = {
                    viewModel.deleteStopWatch()
                    showDeleteWatchDialog = false
                    showEditStopWatchDialog = false
                }) {
                    FlayText(text = "삭제")
                }
            }, secondaryButton = {
                FlayButton(onClick = {
                    showDeleteWatchDialog = false
                }) {
                    FlayText(text = "아니요")
                }
            }
            , onDismiss = {
                showDeleteWatchDialog = false
            })
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

        items(value.stopWatchList, key = { it.idx }) {
            FlayLazyColumnItem(
                onClick = {
                    showEditStopWatchDialog = true
                    viewModel.updateClickedStopWatch(it)
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    FlayText(
                        text = it.title,
                        modifier = Modifier
                            .padding(top = 35.dp, start = 13.dp)
                            .height(30.dp),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .padding(start = 13.dp, bottom = 13.dp)
                    ) {
                        var deadline by remember { mutableStateOf(calculateRemainingTime(it.deadline)) }
                        LaunchedEffect(calculateRemainingTime(it.deadline)) {
                            while (true) {
                                delay(1000)
                                deadline = calculateRemainingTime(it.deadline)
//                            Log.d("TAG", "${it.deadline} - StopWatch() called")
                            }
                        }

                        if (isTimeOver(deadline)) {
                            FlayText(text = "끝났어요", fontSize = 24.sp)
                        } else {
                            val year = deadline.year
                            val month = deadline.month
                            val modifier = Modifier.padding(bottom = 3.dp, end = 4.dp)
                            if (year > 0) {
                                FlayText(text = year.toString(), fontSize = 24.sp)
                                FlayText(text = "년", modifier = modifier)
                            }
                            if (month > 0) {
                                FlayText(text = month.toString(), fontSize = 24.sp)
                                FlayText(text = "월", modifier = modifier)
                            }
                            if (deadline.day > 0) {
                                FlayText(text = deadline.day.toString(), fontSize = 24.sp)
                                FlayText(text = "일", modifier = modifier)
                            }
                            val text = "%02d:%02d:%02d".format(deadline.hour, deadline.minute, deadline.second)
                            FlayText(text = text, fontSize = 24.sp)
                        }
                    }
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
