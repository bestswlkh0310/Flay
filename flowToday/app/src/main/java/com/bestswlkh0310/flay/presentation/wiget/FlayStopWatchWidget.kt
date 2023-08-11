package com.bestswlkh0310.flay.presentation.wiget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.appwidget.CheckBox
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.RadioButton
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.Switch
import androidx.glance.appwidget.provideContent

class FlayStopWatchWidget : GlanceAppWidget() {
    companion object {
        private val SMALL_SQUARE = DpSize(100.dp, 100.dp)
        private val HORIZONTAL_RECTANGLE = DpSize(250.dp, 100.dp)
        private val BIG_SQUARE = DpSize(250.dp, 250.dp)
    }

    override val sizeMode = SizeMode.Responsive(
        setOf(
            SMALL_SQUARE,
            HORIZONTAL_RECTANGLE,
            BIG_SQUARE
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            MyContent()
        }
    }

    @Composable
    private fun MyContent() {
        var isApplesChecked by remember { mutableStateOf(false) }
        var isEnabledSwitched by remember { mutableStateOf(false) }
        var isRadioChecked by remember { mutableStateOf(0) }

        CheckBox(
            checked = isApplesChecked,
            onCheckedChange = { isApplesChecked = !isApplesChecked },
            text = "Apples"
        )

        Switch(
            checked = isEnabledSwitched,
            onCheckedChange = { isEnabledSwitched = !isEnabledSwitched },
            text = "Enabled"
        )

        RadioButton(
            checked = isRadioChecked == 1,
            onClick = { isRadioChecked = 1 },
            text = "Checked"
        )
    }
}