package com.bestswlkh0310.flay.presentation.ui.feature.graph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.flay.data.repository.TodoRepository
import com.bestswlkh0310.flay.domain.model.TodoDto
import com.bestswlkh0310.flay.domain.model.base.GraphDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

data class GraphState(
    var startDay: LocalDate,
    var todoBarList: MutableList<GraphDto<List<TodoDto>>> = arrayListOf(),
    var selectedBar: List<TodoDto> = arrayListOf(),
    var selectedBarDate: String = "",
)

sealed class SideEffect {
    object WrongDate: SideEffect()
    object None: SideEffect()
}

@HiltViewModel
class GraphViewModel @Inject constructor(
    private val todoRepository: TodoRepository
): ViewModel() {
    private val _state = MutableStateFlow(GraphState(
        startDay = LocalDate.now(),
        selectedBarDate = LocalDate.now().let { it.month.value.toString() + "/" + it.dayOfMonth.toString() }
    ))
    val state = _state

    private val _sideEffect = MutableStateFlow<SideEffect>(SideEffect.None)
    val sideEffect = _sideEffect

    private fun loadWeek() {
        val list: MutableList<GraphDto<List<TodoDto>>> = arrayListOf()
        val startDay = _state.value.startDay
        viewModelScope.launch {
            for (i in 0 until 7) {
                val todoList = todoRepository.getTodoByLocalDate(startDay.plusDays(i.toLong()))
                list.add(GraphDto(
                    x = startDay.month.value.toString() + "/" + startDay.plusDays(i.toLong()).dayOfMonth.toString(),
                    y = todoList.filter { it.isDone }.size.toFloat(),
                    y2 = todoList.size.toFloat(),
                    data = todoList
                ))
            }
            _state.value = _state.value.copy(todoBarList = list)
        }
    }

    fun loadTodayList() {
        viewModelScope.launch {
            val list = todoRepository.getTodoByLocalDate(LocalDate.now())
            _state.value = _state.value.copy(selectedBar = list)
        }
    }

    fun loadToday() {
        val today = LocalDate.now()
        val currentDayOfWeek = today.dayOfWeek
        val startOfWeek = today.minusDays(7 - 1 - DayOfWeek.SUNDAY.value.toLong() + currentDayOfWeek.value.toLong())
        _state.value = _state.value.copy(startDay = startOfWeek)
        loadWeek()
    }

    fun loadLeft() {
        _state.value = _state.value.copy(startDay = _state.value.startDay.minusWeeks(1))
        loadWeek()
    }

    fun loadRight() {
        _state.value = _state.value.copy(startDay = _state.value.startDay.plusWeeks(1))
        loadWeek()
    }

    fun updateSelectedTodo(todo: List<TodoDto>, date: String) {
        _state.value = _state.value.copy(selectedBar = todo, selectedBarDate = date)
    }
}
