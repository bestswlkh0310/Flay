package com.bestswlkh0310.flay.presentation.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bestswlkh0310.flay.presentation.ui.feature.graph.GraphScreen
import com.bestswlkh0310.flay.presentation.ui.feature.home.HomeScreen

@Composable
fun NavigationGraph(
    stopWatchLazyListState: LazyListState,
    todoLazyListState: LazyListState
) {
    val navController = rememberNavController()
    val routeAction = remember(navController) { FlayNavigationActions(navController) }

    NavHost(
        navController = navController,
        startDestination = NavGraph.HOME.title
    ) {
        composable(NavGraph.HOME.title) {
            HomeScreen(
                routeAction = routeAction,
                stopWatchLazyListState = stopWatchLazyListState,
                todoLazyListState = todoLazyListState
            )
        }

        composable(NavGraph.GRAPH.title) {
            GraphScreen(
                routeAction = routeAction
            )
        }
    }
}