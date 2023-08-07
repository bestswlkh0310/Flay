package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.flay.presentation.ui.modifier.drawColoredShadow

@Composable
fun FlayLazyColumnItem(
    modifier: Modifier = Modifier,
    height: Dp = 120.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .drawColoredShadow(Color.Black, 0.04f, 15.dp, 4.dp, 1.5.dp, 0.0.dp)
            .background(
                if (isSystemInDarkTheme()) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = 9.dp)
    ) {
        content()
    }
}
