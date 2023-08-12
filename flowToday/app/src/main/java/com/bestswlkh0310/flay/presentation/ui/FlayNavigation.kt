package com.bestswlkh0310.flay.presentation.ui

import androidx.navigation.NavHostController

enum class NavGraph(
    val title: String
) {
    HOME("홈"),
    STOPWATCH("스톱워치"),
    TODO("할 일"),
    GRAPH("통계")
}


class FlayNavigationActions(
    navHostController: NavHostController
) {
    val toGraph: () -> Unit = {
        navHostController.navigate(NavGraph.GRAPH.title)
    }

    val toBack: () -> Unit = {
        navHostController.navigateUp()
    }
}