package com.bestswlkh0310.flay.ui

import androidx.navigation.NavHostController

enum class NAV_ROUTE(
    val title: String,
    val isSingleTon: Boolean,
) {
    STOPWATCH("스톱워치",  true),
    TODO("할 일",  true)
}


class FlayNavigationActions(navHostController: NavHostController) {/*
    val navTo: (NAV_ROUTE) -> Unit = {
        navHostController.navigate(it.title) {
            launchSingleTop = it.isSingleTon
        }
    }

    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }*/
}
