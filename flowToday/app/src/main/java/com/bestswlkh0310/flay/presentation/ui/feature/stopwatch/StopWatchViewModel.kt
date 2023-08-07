package com.bestswlkh0310.flay.presentation.ui.feature.stopwatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.flay.data.entity.StopWatchEntity
import com.bestswlkh0310.flay.data.repository.StopWatchRepository
import com.bestswlkh0310.flay.domain.model.StopWatchDto
import com.bestswlkh0310.flay.presentation.ui.utils.TimeCalculator.localDateTimeToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class AddStopWatchState(
    val titleText: String = "",
    val deadline: LocalDateTime = LocalDateTime.now(),
    var stopWatchList: List<StopWatchDto> = arrayListOf(),
    var clickedStopWatch: StopWatchDto = StopWatchDto(
        0,
        "..",
        localDateTimeToString(LocalDateTime.now())
    )
)

@HiltViewModel
class StopWatchViewModel @Inject constructor(
    private val stopWatchRepository: StopWatchRepository
): ViewModel() {
    private val _state = MutableStateFlow<AddStopWatchState>(AddStopWatchState())
    val state = _state

    fun updateAddStopWatchTitleText(it: String) {
        _state.value = _state.value.copy(titleText = it)
    }

    fun updateAddStopWatchDeadline(deadline: LocalDateTime) {
        _state.value = _state.value.copy(deadline = deadline)
    }

    fun addStopWatch() {
        viewModelScope.launch {
            stopWatchRepository.addStopWatch(
                StopWatchEntity(
                    title = _state.value.titleText,
                    deadline = localDateTimeToString(_state.value.deadline)
                )
            )
            loadStopWatchList()
        }
    }

    fun loadStopWatchList() {
        viewModelScope.launch {
            _state.value = _state.value.copy(stopWatchList = stopWatchRepository.getStopWatchList())
        }
    }

    fun updateEditStopWatchTitleText(it: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(clickedStopWatch = _state.value.clickedStopWatch.copy(title = it))
        }
    }

    fun updateClickedStopWatch(stopWatchDto: StopWatchDto) {
        _state.value = _state.value.copy(clickedStopWatch = stopWatchDto)
    }

    fun updateEditStopWatchDeadline(deadline: LocalDateTime) {
        _state.value = _state.value.copy(clickedStopWatch = _state.value.clickedStopWatch.copy(deadline = localDateTimeToString(deadline)))
    }

    fun updateStopWatchComplete() {
        viewModelScope.launch {
            stopWatchRepository.updateStopWatch(_state.value.clickedStopWatch)
            loadStopWatchList()
        }
    }
}