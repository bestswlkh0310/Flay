package com.bestswlkh0310.flay.presentation.widget.feature.stopwatch

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.text.Text

import androidx.glance.appwidget.lazy.items
import androidx.glance.background
import com.bestswlkh0310.flay.presentation.widget.component.FlayWidgetLazyColumn

@Composable
fun StopWatchWidgetScreen(
) {
    FlayWidgetLazyColumn(
        modifier = GlanceModifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(arrayListOf(1, 2, 3)) {
            Text(text = it.toString())
        }
    }
}
