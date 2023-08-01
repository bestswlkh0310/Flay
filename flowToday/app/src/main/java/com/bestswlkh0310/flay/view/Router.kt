package com.bestswlkh0310.flay.view

import android.util.Log
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.dto.StopWatchDto
import com.bestswlkh0310.flay.theme.main150
import com.bestswlkh0310.flay.theme.main30
import com.bestswlkh0310.flay.theme.main50
import com.bestswlkh0310.flay.view.stopwatch.StopWatch
import com.bestswlkh0310.flay.view.todo.Todo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class RouteAction(navHostController: NavHostController) {
    val navTo: (NAV_ROUTE) -> Unit = {
        navHostController.navigate(it.title) {
            launchSingleTop = it.isSingleTon
        }
    }

    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }
}

enum class NAV_ROUTE(
    val title: String,
    val isSingleTon: Boolean,
) {
    STOPWATCH("스톱워치",  true),
    TODO("할 일",  true)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    val routeAction = remember(navController) { RouteAction(navController) }
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val isScrollDown by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }
    val targetAlpha = if (isScrollDown) 1f else 0f
    val fabVisibleState = animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(500)
    )

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController, routeAction = routeAction) },
        floatingActionButton = {
            Column() {
            }
            Box(modifier = Modifier
                .alpha(fabVisibleState.value)) {
                FlayIconButton(
                    modifier = Modifier
                        .drawColoredShadow(Color.Black, 0.1f, 50.dp, 4.dp, 1.5.dp, 0.dp),
                    iconId = R.drawable.ic_up,
                    contentDescription = "up",
                    containerColor = main30,
                    size = 29.dp
                ) {
                    coroutineScope.launch {
                        Log.d("TAG", "click - MainScreenView() called")
                        lazyListState.animateScrollToItem(0)
                    }
                    Log.d(
                        "TAG",
                        "${lazyListState.firstVisibleItemIndex}, ${lazyListState.firstVisibleItemScrollOffset}- MainScreenView() called"
                    )
                    // TODO: ㅇㅏ래로 내리면 나타나고 클릭하면 맨 위로 올라가도록
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

@Composable
fun BottomNavigation(
    navController: NavHostController,
    routeAction: RouteAction
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
                            color = main150,
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


@Composable
fun NavigationGraph(
    navController: NavHostController,
    routeAction: RouteAction,
    lazyListState: LazyListState
) {


    NavHost(navController = navController, startDestination = NAV_ROUTE.STOPWATCH.title) {
        composable(NAV_ROUTE.STOPWATCH.title) {
            StopWatch(routeAction, lazyListState = lazyListState)
        }

        composable(NAV_ROUTE.TODO.title) {
            Todo()
        }
    }
}