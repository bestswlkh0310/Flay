package com.bestswlkh0310.flay.presentation.widget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.bestswlkh0310.flay.presentation.widget.feature.stopwatch.StopWatchWidget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlayWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = StopWatchWidget()
}