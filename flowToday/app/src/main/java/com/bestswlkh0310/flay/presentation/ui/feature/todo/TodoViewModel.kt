package com.bestswlkh0310.flay.presentation.ui.feature.todo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.flay.data.repository.TodoRepository
import com.bestswlkh0310.flay.domain.model.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class TodoState(
    val todo: String = "",
    val todayTodoList: List<TodoDto?> = arrayListOf(),
    val clickedTodo: TodoDto = TodoDto(
        idx = -1,
        "",
        LocalDate.now(),
        false,
        1
    )
)

sealed class SideEffect {
    object None: SideEffect()
    object WrongTodo: SideEffect()
    object EditTodoComplete: SideEffect()
}

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TodoState())
    val state = _state

    private val _sideEffect = MutableStateFlow<SideEffect>(SideEffect.None)
    val sideEffect = _sideEffect

    fun updateTodo(it: String) {
        updateState(getState().copy(todo = it))
    }

    private fun updateState(todoState: TodoState) {
        _state.value = todoState
    }

    private fun getState() = _state.value

    fun loadTodoList() {
        viewModelScope.launch {
            val addList = arrayListOf<TodoDto?>(null)
            addList.addAll(todoRepository.getTodoByLocalDate(LocalDate.now()).sortedBy { it.position })
            _state.value = getState().copy(todayTodoList = addList)
        }
    }

    fun addTodo() {
        if (_state.value.todo.isEmpty()) {
            _sideEffect.value = SideEffect.WrongTodo
            return
        }

        viewModelScope.launch {
            todoRepository.addTodo(
                TodoDto(
                    idx = 0,
                    todo = _state.value.todo,
                    createdTime = LocalDate.now(),
                    isDone = false,
                    position = _state.value.todayTodoList.size
                )
            )
            removeAddTodo()
            loadTodoList()
        }
    }

    fun updateTodoCheck(todoDto: TodoDto) {
        viewModelScope.launch {
            todoRepository.updateTodo(todoDto.copy(isDone = !todoDto.isDone))
            loadTodoList()
        }
    }

    fun replaceTodo(fromPos: Int, toPos: Int) {
        viewModelScope.launch {
            val todoList = _state.value.todayTodoList

            val fromDto = todoList[fromPos]
            val toDto = todoList[toPos]

            todoRepository.updateTodo(fromDto!!.copy(position = toPos))
            todoRepository.updateTodo(toDto!!.copy(position = fromPos))
            loadTodoList()
        }
    }

    fun updateClickedTodo(todo: TodoDto) {
        _state.value = _state.value.copy(clickedTodo = todo)
    }

    fun deleteTodo() {
        val value = _state.value
        viewModelScope.launch {
            val delPosition = value.clickedTodo.position
            todoRepository.deleteTodo(value.clickedTodo)
            value.todayTodoList.forEach {
                if (it != null && it.position > delPosition) {
                    it.position--
                }
            }
            loadTodoList()
        }
    }

    fun updateTodoComplete() {
        viewModelScope.launch {
            todoRepository.updateTodo(_state.value.clickedTodo)
            _sideEffect.value = SideEffect.EditTodoComplete
            loadTodoList()
        }
    }

    fun updateEditTodoText(it: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(clickedTodo = _state.value.clickedTodo.copy(todo = it))
        }
    }

    private fun removeAddTodo() {
        _state.value = _state.value.copy(todo = "")
    }

    fun noneEffect() {
        _sideEffect.value = SideEffect.None
    }
}