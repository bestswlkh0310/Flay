package com.bestswlkh0310.flay.presentation.ui.feature.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bestswlkh0310.flay.R
import com.bestswlkh0310.flay.presentation.ui.FlayNavigationActions
import com.bestswlkh0310.flay.presentation.ui.component.FlayBarGraph
import com.bestswlkh0310.flay.presentation.ui.component.FlayIconButton
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumn
import com.bestswlkh0310.flay.presentation.ui.component.FlayLazyColumnItem
import com.bestswlkh0310.flay.presentation.ui.component.FlayText
import com.bestswlkh0310.flay.presentation.ui.component.FlayTopBar

@Composable
fun GraphScreen(
    routeAction: FlayNavigationActions,
    viewModel: GraphViewModel = hiltViewModel()
) {
    FlayTopBar(
        titleText = "통계",
        primaryButtonCallback = {
            routeAction.toBack()
        }
    ) {
        FlayLazyColumn(
            modifier = Modifier
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .padding(horizontal = 1.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FlayIconButton(
                        iconId = R.drawable.ic_back,
                        contentDescription = "back",
                        size = 18.dp
                    ) {

                    }
                    FlayText(text = "8/7 ~ 8/12", fontSize = 18.sp)
                    FlayIconButton(
                        iconId = R.drawable.ic_front,
                        contentDescription = "back",
                        size = 18.dp
                    ) {

                    }
                }

                FlayLazyColumnItem(
                    modifier = Modifier
                        .padding(top = 25.dp)
                ) {
                    FlayBarGraph(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .padding(bottom = 10.dp),
                        height = 150.dp
                    )
                }
            }
        }
    }
}