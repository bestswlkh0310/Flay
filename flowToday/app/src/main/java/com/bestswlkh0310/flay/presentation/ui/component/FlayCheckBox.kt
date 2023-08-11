package com.bestswlkh0310.flay.presentation.ui.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun FlayCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    shape: Shape = RoundedCornerShape(50),
    onChange: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(size = 22.dp)
            .clip(shape = shape)
            .clickable {
                onChange()
            }
            .background(
                color = if (checked) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = if (checked) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                modifier = Modifier.scale(0.7f),
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}