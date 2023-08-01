package com.bestswlkh0310.flay.view.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Todo() {
    Column(modifier = Modifier.background(Color.Yellow)) {
        Text(text = "투두 리스트")
    }
}