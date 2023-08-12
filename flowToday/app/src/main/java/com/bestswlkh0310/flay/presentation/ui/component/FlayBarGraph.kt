package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@Composable
fun FlayBarGraph(
    modifier: Modifier = Modifier,
    data: List<Int> = arrayListOf(1, 2, 3, 1, 2, 1),
    height: Dp
) {
    val min = data.sortedBy { it }.first()
    val max = data.sortedBy { it }.last()
    val b = height / max
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            items(data) {
                // TODO : fix width
                FlayBar(height = it * b, width = 11.dp)
            }
        }
        Divider(color = MaterialTheme.colorScheme.background)
    }
}