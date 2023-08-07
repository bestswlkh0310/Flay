package com.bestswlkh0310.flay.presentation.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bestswlkh0310.flay.presentation.ui.component.FlayBottomButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayText
import com.bestswlkh0310.flay.presentation.ui.component.FlayBottomNavigation

@Composable
fun FlayBottomNavigation(
    navController: NavHostController,
    routeAction: FlayNavigationActions
) {
    val items = listOf(
        NAV_ROUTE.STOPWATCH,
        NAV_ROUTE.TODO,
    )

    FlayBottomNavigation(
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .padding(bottom = 4.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            val selected = item.title == currentRoute
            FlayBottomButton(
                onClick = {
                    navController.navigate(item.title) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                Column (
                    modifier = Modifier
                        .height(38.dp)
                        .width(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FlayText(item.title,
                        modifier = Modifier
                            .height(34.dp))
                    val transition = updateTransition(targetState = selected, label = "dividerAnimation")
                    val animatedOpacity by transition.animateFloat(
                        transitionSpec = { tween(durationMillis = 500) },
                        label = "opacity"
                    ) {
                        if (it) 1f else 0f
                    }
                    if (item.title == currentRoute) {
                        Divider(
                            color = MaterialTheme.colorScheme.secondary,
                            thickness = 2.dp,
                            modifier = Modifier
                                .width((item.title.length * 10).dp)
                                .padding(top = 2.dp)
                                .graphicsLayer(
                                    alpha = animatedOpacity,
                                )
                        )
                    }
                }
            }
        }
    }
}
