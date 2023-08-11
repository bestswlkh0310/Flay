package com.bestswlkh0310.flay.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.presentation.ui.component.FlayIconButton
import com.bestswlkh0310.flay.presentation.ui.modifier.drawColoredShadow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlayApp() {
    val navController = rememberNavController()
    val routeAction = remember(navController) { FlayNavigationActions(navController) }
    val coroutineScope = rememberCoroutineScope()

    var currentRoute by remember { mutableStateOf(NAV_ROUTE.STOPWATCH.title) }

    val stopWatchLazyListState = rememberLazyListState()
    val todoLazyListState = rememberLazyListState()
    var isScrollDown by remember { mutableStateOf(false) }

    if (currentRoute.equals(NAV_ROUTE.STOPWATCH.title)) {
        isScrollDown = stopWatchLazyListState.firstVisibleItemIndex > 0
    } else if (currentRoute.equals(NAV_ROUTE.TODO.title)) {
        isScrollDown = todoLazyListState.firstVisibleItemIndex > 0
    }

    val fabBottomPadding = animateDpAsState(
        targetValue = if (currentRoute == NAV_ROUTE.TODO.title) 60.dp else 0.dp,
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
                navController = navController,
                routeAction = routeAction
            ) {
                currentRoute = it
            }
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
                        if (currentRoute == NAV_ROUTE.STOPWATCH.title) {
                            stopWatchLazyListState.animateScrollToItem(0)
                        } else if (currentRoute == NAV_ROUTE.TODO.title) {
                            todoLazyListState.animateScrollToItem(0)
                        }
                    }
                }
            }
        },
        containerColor = Color.Transparent
    ) {
        Box(Modifier.padding(it)) {
            NavigationGraph(
                navController = navController,
                routeAction = routeAction,
                stopWatchLazyListState = stopWatchLazyListState,
                todoLazyListState = todoLazyListState
            )
        }
    }
}

