package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FlayTextField(
    value: String,
    modifier: Modifier = Modifier,
    onChange: (String) -> Unit,
    paddingHorizontal: Dp = 8.dp,
    paddingVertical: Dp = 8.dp
) {
    Column(
        modifier = Modifier
            .padding(
                vertical = paddingVertical,
                horizontal = paddingHorizontal
            ),
    ) {
        BasicTextField(
            value = value,
            modifier = modifier,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
            ),
            onValueChange = onChange,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
        )
        Spacer(
            modifier = Modifier.height(3.dp)
        )
        Divider(
            modifier = Modifier.padding(horizontal = paddingHorizontal),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
