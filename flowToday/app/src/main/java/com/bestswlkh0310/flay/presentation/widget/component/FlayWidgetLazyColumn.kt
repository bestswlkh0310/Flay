package com.bestswlkh0310.flay.presentation.widget.component


import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.LazyListScope
import androidx.glance.layout.Alignment
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding

@Composable
fun FlayWidgetLazyColumn(
    modifier: GlanceModifier = GlanceModifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 9.dp)
            .height(500.dp)
            .fillMaxWidth(),
        horizontalAlignment = horizontalAlignment,
        content = content,
    )
}

