package com.bestswlkh0310.flay.ui.stopwatch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime

data class AddStopWatchState(
    val titleText: String = "",
    val deadline: LocalDateTime = LocalDateTime.now()
)

class StopWatchViewModel: ViewModel() {
    private val _state = MutableStateFlow<AddStopWatchState>(AddStopWatchState())
    val state = _state

    fun updateTitleText(it: String) {
        _state.value = _state.value.copy(titleText = it)
    }

    fun updateDeadline(deadline: LocalDateTime) {
        _state.value = _state.value.copy(deadline = deadline)
    }
}