package com.bestswlkh0310.flay.presentation.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("ModifierParameter")
@Composable
fun FlayTitle(
    text: String,
    marginTop: Dp = 80.dp,
    marginBottom: Dp = 30.dp,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(marginTop))
    FlayText(
        text = text,
        fontSize = 24.sp,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(marginBottom))
}