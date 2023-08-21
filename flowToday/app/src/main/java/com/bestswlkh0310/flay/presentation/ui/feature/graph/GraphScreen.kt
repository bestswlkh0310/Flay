package com.bestswlkh0310.flay.presentation.ui.feature.graph

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.domain.model.TodoDto
import com.bestswlkh0310.flay.domain.model.base.GraphDto
import com.bestswlkh0310.flay.presentation.ui.FlayNavigationActions
import com.bestswlkh0310.flay.presentation.ui.component.FlayBarGraph
import com.bestswlkh0310.flay.presentation.ui.component.FlayCheckBox
import com.bestswlkh0310.flay.presentation.ui.component.FlayColumn
import com.bestswlkh0310.flay.presentation.ui.component.FlayIconButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumn
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumnItem
import com.bestswlkh0310.flay.presentation.ui.component.FlaySubTitle
import com.bestswlkh0310.flay.presentation.ui.component.FlayText
import com.bestswlkh0310.flay.presentation.ui.component.FlayTopBar
import com.bestswlkh0310.flay.presentation.ui.modifier.flayClickable
import java.time.LocalDate

@Composable
fun GraphScreen(
    routeAction: FlayNavigationActions,
    viewModel: GraphViewModel = hiltViewModel()
) {
    val value = viewModel.state.collectAsState().value
    val state = viewModel.sideEffect.collectAsState().value

    LaunchedEffect(true) {
        viewModel.loadToday()
        viewModel.loadTodayList()
    }

    value.todoBarList.forEach {
        Log.d("TAG", "$it - GraphScreen() called")
    }

    FlayTopBar(
        titleText = "통계",
        primaryButtonCallback = {
            routeAction.toBack()
        }
    ) {
        FlayColumn(
            modifier = Modifier,
            height = 350.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(horizontal = 1.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FlayIconButton(
                    iconId = R.drawable.ic_back,
                    contentDescription = "back",
                    size = 16.dp,
                    alpha = 0.5f
                ) {
                    viewModel.loadLeft()
                }
                val startDay = value.startDay
                val endDay = startDay.plusDays(6)
                FlayText(text = "" +
                        "${startDay.month.value}/${startDay.dayOfMonth}" +
                        " ~ " +
                        "${endDay.month.value}/${endDay.dayOfMonth}", fontSize = 18.sp)
                FlayIconButton(
                    iconId = R.drawable.ic_front,
                    contentDescription = "back",
                    size = 16.dp,
                    alpha = 0.5f
                ) {
                    viewModel.loadRight()
                }
            }
            FlayLazyColumnItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
            ) {
                FlayBarGraph(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                        .padding(bottom = 10.dp),
                    height = 140.dp,
                    data = value.todoBarList,
                    labels = arrayListOf(
                        "할 일" to MaterialTheme.colorScheme.tertiary,
                        "완료" to MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                    )
                ) {
                    Log.d("TAG", "$it - GraphScreen() called")
                    viewModel.updateSelectedTodo(it.data, it.x)
                }
            }
        }
        FlaySubTitle(text = value.selectedBarDate + " 할 일")
        FlayLazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(top = 9.dp),
            horizontalAlignment = Alignment.Start
        ) {
            items(value.selectedBar) {
                FlayLazyColumnItem {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(7.dp))
                        FlayCheckBox(
                            checked = it.isDone
                        ) {}
                        Spacer(modifier = Modifier.width(10.dp))
                        FlayText(
                            modifier = Modifier,
                            text = it.todo,
                            color = MaterialTheme.colorScheme.secondary,
                            textDecoration = if (it.isDone) TextDecoration.LineThrough else TextDecoration.None
                        )
                    }
                }
            }
        }
    }
}