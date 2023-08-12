package com.bestswlkh0310.flay.presentation.ui.feature.graph

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.domain.model.TodoDto
import com.bestswlkh0310.flay.domain.model.base.GraphDto
import com.bestswlkh0310.flay.presentation.ui.FlayNavigationActions
import com.bestswlkh0310.flay.presentation.ui.component.FlayBarGraph
import com.bestswlkh0310.flay.presentation.ui.component.FlayIconButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumn
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumnItem
import com.bestswlkh0310.flay.presentation.ui.component.FlayText
import com.bestswlkh0310.flay.presentation.ui.component.FlayTopBar
import java.time.LocalDate

val dummy = arrayListOf(
    GraphDto("8/7", 1.0f, TodoDto(1, "", LocalDate.now(), false, 0)),
    GraphDto("8/8", 2.0f, TodoDto(1, "", LocalDate.now(), false, 0)),
    GraphDto("8/9", 3.0f, TodoDto(1, "", LocalDate.now(), false, 0)),
    GraphDto("8/10", 1.0f, TodoDto(1, "", LocalDate.now(), false, 0)),
    GraphDto("8/11", 9.0f, TodoDto(1, "", LocalDate.now(), false, 0)),
    GraphDto("8/12", 7.0f, TodoDto(1, "", LocalDate.now(), false, 0)),
)

@Composable
fun GraphScreen(
    routeAction: FlayNavigationActions,
    viewModel: GraphViewModel = hiltViewModel()
) {

    val value = viewModel.state.collectAsState().value
    val state = viewModel.sideEffect.collectAsState().value

    LaunchedEffect(true) {
        viewModel.loadToday()
    }

    value.todoBarList.forEach {
        Log.d("TAG", "${it} - GraphScreen() called")
    }

    FlayTopBar(
        titleText = "통계",
        primaryButtonCallback = {
            routeAction.toBack()
        }
    ) {
        FlayLazyColumn(
            modifier = Modifier
        ) {
            item {
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
                        height = 150.dp,
                        data = value.todoBarList
                    ) {
                        Log.d("TAG", "$it - GraphScreen() called")
                    }
                }
            }
        }
    }
}