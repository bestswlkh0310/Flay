package com.bestswlkh0310.flay.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.ui.theme.primary
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlayApp() {
    val navController = rememberNavController()
    val routeAction = remember(navController) { FlayNavigationActions(navController) }
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val isScrollDown by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }
    val targetAlpha = if (isScrollDown) 1f else 0f
    val fabVisibleState = animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(500)
    )

    Scaffold(
        bottomBar = {
            FlayBottomNavigation(
                navController = navController,
                routeAction = routeAction
            )
        },
        floatingActionButton = {
            Box(modifier = Modifier
                .alpha(fabVisibleState.value)) {
                FlayIconButton(
                    modifier = Modifier
                        .drawColoredShadow(Color.Black, 0.1f, 50.dp, 4.dp, 1.5.dp, 0.dp),
                    iconId = R.drawable.ic_up,
                    contentDescription = "up",
                    containerColor = primary,
                    size = 29.dp
                ) {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                }
            }
        },
        containerColor = Color.Transparent
    ) {
        Box(Modifier.padding(it)){
            NavigationGraph(
                navController = navController,
                routeAction = routeAction,
                lazyListState = lazyListState
            )
        }
    }
}
