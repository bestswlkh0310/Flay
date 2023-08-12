package com.bestswlkh0310.flay.presentation.ui.feature.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.presentation.ui.FlayBottomNavigation
import com.bestswlkh0310.flay.presentation.ui.FlayNavigationActions
import com.bestswlkh0310.flay.presentation.ui.NavGraph
import com.bestswlkh0310.flay.presentation.ui.component.FlayIconButton
import com.bestswlkh0310.flay.presentation.ui.feature.stopwatch.StopWatchScreen
import com.bestswlkh0310.flay.presentation.ui.feature.todo.TodoScreen
import com.bestswlkh0310.flay.presentation.ui.modifier.drawColoredShadow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    routeAction: FlayNavigationActions,
    stopWatchLazyListState: LazyListState,
    todoLazyListState: LazyListState
) {
    val coroutineScope = rememberCoroutineScope()

    var isScrollDown by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(NavGraph.TODO.title) }

    if (selectedTab == NavGraph.STOPWATCH.title) {
        isScrollDown = stopWatchLazyListState.firstVisibleItemIndex > 0
    } else if (selectedTab == NavGraph.TODO.title) {
        isScrollDown = todoLazyListState.firstVisibleItemIndex > 0
    }

    val fabBottomPadding = animateDpAsState(
        targetValue = if (selectedTab == NavGraph.TODO.title) 60.dp else 0.dp,
        animationSpec = tween(500), label = ""
    )

    val targetAlpha = if (isScrollDown) 1f else 0.0f
    val fabVisibleState = animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(500), label = ""
    )

    Scaffold(
        bottomBar = {
            FlayBottomNavigation(
                selectedTab = selectedTab,
                selectedTabCallback = {
                    selectedTab = it
                }
            )
        },
        floatingActionButton = {
            Box(modifier = Modifier
                .alpha(fabVisibleState.value)
                .padding(bottom = fabBottomPadding.value)
            ) {
                // UP BUTTON
                FlayIconButton(
                    modifier = Modifier
                        .drawColoredShadow(Color.Black, 0.1f, 70.dp, 4.dp, 1.5.dp, 0.dp),
                    iconId = R.drawable.ic_up,
                    contentDescription = "up",
                    containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary,
                    size = 29.dp
                ) {
                    coroutineScope.launch {
                        if (selectedTab == NavGraph.STOPWATCH.title) {
                            stopWatchLazyListState.animateScrollToItem(0)
                        } else if (selectedTab == NavGraph.TODO.title) {
                            todoLazyListState.animateScrollToItem(0)
                        }
                    }
                }
            }
        },
        containerColor = Color.Transparent
    ) {
        Box(Modifier.padding(it)) {
            when (selectedTab) {
                NavGraph.STOPWATCH.title -> StopWatchScreen(lazyListState = stopWatchLazyListState)
                NavGraph.TODO.title -> TodoScreen(routeAction = routeAction, lazyListState = todoLazyListState)
            }
        }
    }
}