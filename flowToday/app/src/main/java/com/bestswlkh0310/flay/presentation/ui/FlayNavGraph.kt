package com.bestswlkh0310.flay.presentation.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bestswlkh0310.flay.presentation.ui.feature.stopwatch.StopWatchScreen
import com.bestswlkh0310.flay.presentation.ui.feature.todo.TodoScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    routeAction: FlayNavigationActions,
    stopWatchLazyListState: LazyListState,
    todoLazyListState: LazyListState
) {
    NavHost(navController = navController, startDestination = NAV_ROUTE.TODO.title) {
        composable(NAV_ROUTE.STOPWATCH.title) {
            StopWatchScreen(routeAction, lazyListState = stopWatchLazyListState)
        }

        composable(NAV_ROUTE.TODO.title) {
            TodoScreen(routeAction , todoLazyListState)
        }
    }
}