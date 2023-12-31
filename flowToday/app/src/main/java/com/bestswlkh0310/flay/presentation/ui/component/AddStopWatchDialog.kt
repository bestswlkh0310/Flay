package com.bestswlkh0310.flay.presentation.ui.component

import android.view.Gravity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.bestswlkh0310.flay.presentation.ui.feature.stopwatch.StopWatchViewModel
import com.bestswlkh0310.flay.presentation.ui.component.wheel_picker_compose.WheelDateTimePicker

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddStopWatch(
    title: String,
    primaryButton: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    secondaryButton: @Composable (() -> Unit)? = null,
    viewModel: StopWatchViewModel
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        ),
    ) {
        val state by viewModel.state.collectAsState()
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM)
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = modifier
                    .background(
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(15.dp),
                    )
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
            ) {
                FlayText(
                    text = title,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(2.dp))
                description?.let {
                    FlayText(
                        text = it,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                FlayTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    value = state.titleText,
                    onChange = { viewModel.updateAddStopWatchTitleText(it) },
                    paddingHorizontal = 8.dp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    WheelDateTimePicker(
                        size = DpSize(300.dp, 160.dp),
                        modifier = Modifier.fillMaxWidth(),
                        textColor = MaterialTheme.colorScheme.secondary,
                        textStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
                    ) {
                        viewModel.updateAddStopWatchDeadline(it)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    secondaryButton?.let {
                        it()
                    }
                    primaryButton()
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}