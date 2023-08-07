package com.bestswlkh0310.flay.presentation.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FlayIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    contentDescription: String,
    containerColor: Color = Color.Transparent,
    size: Dp = 20.dp,
    onClick: () -> Unit
) {
    Box {
        FlayButton(
            onClick = onClick,
            modifier = modifier
                .size(size.times(2))
                .clip(CircleShape),
            containerColor = containerColor
        ) {}
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .size(size)
                .offset(x = (size.div(2)), y = size.div(2))
        )
        MaterialTheme.colorScheme
    }
}
