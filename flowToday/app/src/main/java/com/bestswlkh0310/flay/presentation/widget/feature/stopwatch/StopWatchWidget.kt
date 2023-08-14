package com.bestswlkh0310.flay.presentation.widget.feature.stopwatch

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text
import com.bestswlkh0310.flay.presentation.widget.utils.Constants.BIG_SQUARE
import com.bestswlkh0310.flay.presentation.widget.utils.Constants.HORIZONTAL_RECTANGLE

class StopWatchWidget : GlanceAppWidget() {

    override val sizeMode = SizeMode.Responsive(
        setOf(
            HORIZONTAL_RECTANGLE,
            BIG_SQUARE
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            StopWatchWidgetScreen()
        }
    }

}