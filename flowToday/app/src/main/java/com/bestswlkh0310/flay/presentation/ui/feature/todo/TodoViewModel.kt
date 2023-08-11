package com.bestswlkh0310.flay.presentation.ui.feature.todo

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TodoState(
    val todo: String = "",
)

sealed class SideEffect {
    object None: SideEffect()
}

class TodoViewModel: ViewModel() {
    private val _state = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()

    private val _sideEffect = MutableStateFlow<SideEffect>(SideEffect.None)
    val sideEffect = _sideEffect.asStateFlow()

    fun updateTodo(it: String) {
        updateState(getState().copy(todo = it))
    }

    private fun updateState(todoState: TodoState) {
        _state.value = todoState
    }

    private fun getState() = _state.value
}