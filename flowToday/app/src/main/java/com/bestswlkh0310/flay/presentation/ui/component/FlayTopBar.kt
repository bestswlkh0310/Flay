package com.bestswlkh0310.flay.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.flay.R

@Composable
fun FlayTopBar(
    enablePrimaryButton: Boolean = true,
    primaryButtonCallback: () -> Unit = {},
    titleText: String = "",
    body: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            if (enablePrimaryButton) {
                FlayIconButton(
                    modifier = Modifier,
                    iconId = R.drawable.ic_back,
                    contentDescription = "to back",
                    onClick = primaryButtonCallback
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            FlayText(
                text = titleText,
                fontSize = 22.sp,
                modifier = Modifier,
            )
        }
        body()
    }
}