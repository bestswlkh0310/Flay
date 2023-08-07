package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FlayBottomButton(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp),
    ) {
        content()
    }
}