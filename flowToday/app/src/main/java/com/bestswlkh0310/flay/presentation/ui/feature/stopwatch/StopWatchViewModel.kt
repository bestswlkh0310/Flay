package com.bestswlkh0310.flay.presentation.ui.feature.stopwatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.flay.data.entity.StopWatchEntity
import com.bestswlkh0310.flay.data.repository.StopWatchRepository
import com.bestswlkh0310.flay.domain.model.StopWatchDto
import com.bestswlkh0310.flay.domain.model.TodoDto
import com.bestswlkh0310.flay.presentation.ui.utils.TimeCalculator.localDateTimeToString
import com.bestswlkh0310.flay.presentation.ui.utils.TimeCalculator.stringToLocalDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

data class StopWatchState(
    val titleText: String = "",
    val deadline: LocalDateTime = LocalDateTime.now(),
    var stopWatchList: List<StopWatchDto?> = arrayListOf(),
    var clickedStopWatch: StopWatchDto = StopWatchDto(
        0,
        "..",
        localDateTimeToString(LocalDateTime.now()),
        0
    )
)

sealed class SideEffect {
    object WrongTitle: SideEffect()
    object None: SideEffect()
    object AddStopWatchComplete: SideEffect()
    object EditStopWatchComplete: SideEffect()
    object WrongDeadline: SideEffect()
}

@HiltViewModel
class StopWatchViewModel @Inject constructor(
    private val stopWatchRepository: StopWatchRepository
): ViewModel() {
    private val _state = MutableStateFlow(StopWatchState())
    val state = _state

    private val _sideEffect = MutableStateFlow<SideEffect>(SideEffect.None)
    val sideEffect = _sideEffect

    fun updateAddStopWatchTitleText(it: String) {
        _state.value = _state.value.copy(titleText = it)
    }

    fun updateAddStopWatchDeadline(deadline: LocalDateTime) {
        _state.value = _state.value.copy(deadline = deadline)
    }

    fun addStopWatch() {
        if (_state.value.titleText.isEmpty()) {
            _sideEffect.value = SideEffect.WrongTitle
            return
        }
        if (_state.value.deadline.isBefore(LocalDateTime.now())) {
            _sideEffect.value = SideEffect.WrongDeadline
            return
        }
        viewModelScope.launch {
            stopWatchRepository.addStopWatch(
                StopWatchEntity(
                    title = _state.value.titleText,
                    deadline = localDateTimeToString(_state.value.deadline),
                    position = _state.value.stopWatchList.size
                )
            )
            _sideEffect.value = SideEffect.AddStopWatchComplete
            removeAddStopWatch()
            loadStopWatchList()
        }
    }

    private fun removeAddStopWatch() {
        _state.value = _state.value.copy(titleText = "")
    }

    fun loadStopWatchList() {
        viewModelScope.launch {
            val addList = arrayListOf<StopWatchDto?>(null)
            addList.addAll(stopWatchRepository.getStopWatchList().sortedBy { it.position })
            _state.value = _state.value.copy(stopWatchList = addList)
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
        if (_state.value.clickedStopWatch.title.isEmpty()) {
            _sideEffect.value = SideEffect.WrongTitle
            return
        }
        if (stringToLocalDateTime(_state.value.clickedStopWatch.deadline).isBefore(LocalDateTime.now())) {
            _sideEffect.value = SideEffect.WrongDeadline
            return
        }
        viewModelScope.launch {
            stopWatchRepository.updateStopWatch(_state.value.clickedStopWatch)
            _sideEffect.value = SideEffect.EditStopWatchComplete
            loadStopWatchList()
        }
    }

    fun deleteStopWatch() {
        viewModelScope.launch {
            stopWatchRepository.deleteStopWatch(_state.value.clickedStopWatch)
            loadStopWatchList()
        }
    }

    fun replaceStopWatch(fromPos: Int, toPos: Int) {

        viewModelScope.launch {
            val stopWatchList = _state.value.stopWatchList

            val fromDto = stopWatchList[fromPos]
            val toDto = stopWatchList[toPos]

            stopWatchRepository.updateStopWatch(fromDto!!.copy(position = toPos))
            stopWatchRepository.updateStopWatch(toDto!!.copy(position = fromPos))
            loadStopWatchList()
        }
    }

    fun noneEffect() {
        _sideEffect.value = SideEffect.None
    }
}