package com.bestswlkh0310.flay.presentation.ui.feature.todo

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.domain.model.TodoDto
import com.bestswlkh0310.flay.presentation.ui.FlayNavigationActions
import com.bestswlkh0310.flay.presentation.ui.component.FlayButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayCheckBox
import com.bestswlkh0310.flay.presentation.ui.component.FlayIconButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumn
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumnItem
import com.bestswlkh0310.flay.presentation.ui.component.FlayText
import com.bestswlkh0310.flay.presentation.ui.component.FlayTextField
import com.bestswlkh0310.flay.presentation.ui.component.FlayTitle
import com.bestswlkh0310.flay.presentation.ui.modifier.drawColoredShadow
import com.bestswlkh0310.flay.presentation.ui.modifier.flayClickable
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import java.time.LocalDate

@Composable
fun TodoScreen(
    routeAction: FlayNavigationActions? = null,
    lazyListState: LazyListState,
    todoViewModel: TodoViewModel = hiltViewModel()
) {
    val state = todoViewModel.state.collectAsState().value

    val effect = todoViewModel.sideEffect.collectAsState().value
    when (effect) {
        else -> {}
    }
    val items = arrayListOf(
        null,
        TodoDto(1, "1", LocalDate.now(), false),
        TodoDto(2, "2", LocalDate.now(), false),
        TodoDto(3, "3", LocalDate.now(), false),
        TodoDto(4, "4", LocalDate.now(), false),
        TodoDto(5, "5", LocalDate.now(), false),
        TodoDto(6, "6", LocalDate.now(), false),
        TodoDto(7, "7", LocalDate.now(), false),
        TodoDto(8, "8", LocalDate.now(), false),
        TodoDto(9, "9", LocalDate.now(), false),
        TodoDto(10, "10", LocalDate.now(), false),
        TodoDto(11, "11", LocalDate.now(), false),
        TodoDto(12, "12", LocalDate.now(), false),
        TodoDto(13, "13", LocalDate.now(), false),
    )

    val data = remember { mutableStateOf(items) }
    val reorderableLazyListState = rememberReorderableLazyListState(
        onMove = { from, to ->
            val updatedData = ArrayList(data.value) // 새로운 ArrayList 생성
            updatedData.add(to.index, updatedData.removeAt(from.index))
            data.value = updatedData
        },
        canDragOver = { draggedOver, dragging -> draggedOver.index > 0 && dragging.index > 0 },
        listState = lazyListState
    )
    FlayLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .reorderable(reorderableLazyListState)
            .detectReorderAfterLongPress(reorderableLazyListState),
        lazyListState = reorderableLazyListState.listState,
        horizontalAlignment = Alignment.CenterHorizontally) {
        items(data.value, { it?.idx ?: -1 }) { item ->
            if (item == null) {
                Spacer(modifier = Modifier.height(80.dp))
                ReorderableItem(reorderableState = reorderableLazyListState, key = null) {
                    FlayTitle(text = "할 일")
                }
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

                        }
                    )
                }
            } else {
                ReorderableItem(reorderableState = reorderableLazyListState, key = item) { isDragging ->
                    val elevation = animateDpAsState(if (isDragging) 100.dp else 0.dp, label = "")
                    FlayLazyColumnItem(modifier = Modifier.shadow(elevation.value)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.width(7.dp))
                            FlayCheckBox(
                                checked = false
                            ) {

                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            FlayText(
                                modifier = Modifier,
                                text = item.todo,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(52.dp)
                .padding(horizontal = 9.dp)
                .align(Alignment.BottomCenter)
                .drawColoredShadow(
                    color = Color.Black,
                    alpha = 0.08f,
                    borderRadius = 50.dp,
                    shadowRadius = 2.dp,
                    offsetX = (0.0f).dp,
                    offsetY = (1).dp
                )
                .clip(RoundedCornerShape(50))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    FlayTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.todo,
                        onChange = {todoViewModel.updateTodo(it)},
                        paddingHorizontal = 0.dp,
                        paddingVertical = 0.dp
                    )
                }
                FlayButton(
                    onClick = {
                        Log.d("TAG", " - TodoScreen() called")
                    }
                ) {
                    FlayText(
                        modifier = Modifier,
                        text = "할게요",
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
