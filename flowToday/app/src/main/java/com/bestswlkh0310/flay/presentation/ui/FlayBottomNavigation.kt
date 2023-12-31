package com.bestswlkh0310.flay.presentation.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.flay.presentation.ui.component.FlayButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayText
import com.bestswlkh0310.flay.presentation.ui.component.FlayBottomNavigation

@Composable
fun FlayBottomNavigation(
    selectedTab: String,
    selectedTabCallback: (String) -> Unit
) {
    val items = listOf(
        NavGraph.STOPWATCH,
        NavGraph.TODO,
    )

    FlayBottomNavigation(
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .padding(bottom = 4.dp)
    ) {
        items.forEach { item ->
            val selected = item.title == selectedTab
            val transition = updateTransition(targetState = selected, label = "dividerAnimation")
            val animatedOpacity by transition.animateFloat(
                transitionSpec = { tween(durationMillis = 500) },
                label = "opacity"
            ) {
                if (it) 1f else 0f
            }
            FlayButton(
                onClick = {
                    selectedTabCallback(item.title)
                }
            ) {
                Column (
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FlayText(
                        text = item.title,
                        modifier = Modifier
                            .height(26.dp)
                    )
                    if (item.title == selectedTab) {
                        BottomDivider(item = item, animatedOpacity = animatedOpacity)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomDivider(item: NavGraph, animatedOpacity: Float) {
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