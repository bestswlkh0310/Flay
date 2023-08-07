package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.flay.presentation.ui.modifier.drawColoredShadow
import com.bestswlkh0310.flay.presentation.ui.modifier.flayClickable

@Composable
fun FlayLazyColumnItem(
    modifier: Modifier = Modifier,
    height: Dp = 120.dp,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .drawColoredShadow(Color.Black, 0.04f, 15.dp, 4.dp, 1.5.dp, 0.0.dp)
    ) {
        Box(
            modifier = modifier
                .background(
                    if (isSystemInDarkTheme()) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
                )
                .fillMaxWidth()
                .height(height)
                .flayClickable(
                    rippleEnable = true,
                    rippleColor = lerp((MaterialTheme.colorScheme.background), MaterialTheme.colorScheme.secondary, 0.2f)
                ) {
                    onClick()
                }
                .padding(horizontal = 9.dp)
        ) {
            content()
        }
    }
}