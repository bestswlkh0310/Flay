package com.bestswlkh0310.flay.ui.utils.wheel_picker_compose

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.flay.ui.utils.wheel_picker_compose.core.DefaultWheelDateTimePicker
import com.bestswlkh0310.flay.ui.utils.wheel_picker_compose.core.SelectorProperties
import com.bestswlkh0310.flay.ui.utils.wheel_picker_compose.core.TimeFormat
import com.bestswlkh0310.flay.ui.utils.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDateTime

@Composable
fun WheelDateTimePicker(
    modifier: Modifier = Modifier,
    startDateTime: LocalDateTime = LocalDateTime.now(),
    minDateTime: LocalDateTime = LocalDateTime.MIN,
    maxDateTime: LocalDateTime = LocalDateTime.MAX,
    yearsRange: IntRange? = IntRange(LocalDateTime.now().year, 2122),
    timeFormat: TimeFormat = TimeFormat.HOUR_24,
    size: DpSize = DpSize(256.dp, 128.dp),
    rowCount: Int = 3,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    textColor: Color = LocalContentColor.current,
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onSnappedDateTime : (snappedDateTime: LocalDateTime) -> Unit = {}
) {
    DefaultWheelDateTimePicker(
        modifier,
        startDateTime,
        minDateTime,
        maxDateTime,
        yearsRange,
        timeFormat,
        size,
        rowCount,
        textStyle,
        textColor,
        selectorProperties,
        onSnappedDateTime = { snappedDateTime ->
            onSnappedDateTime(snappedDateTime.snappedLocalDateTime)
            snappedDateTime.snappedIndex
        }
    )
}