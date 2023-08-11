package com.bestswlkh0310.flay.data.repository

import com.bestswlkh0310.flay.data.entity.TodoEntity
import com.bestswlkh0310.flay.domain.model.TodoDto
import java.time.LocalDate

interface TodoRepository {
    suspend fun getTodoList(): List<TodoDto>

    suspend fun getTodoByLocalDate(localDate: LocalDate): List<TodoDto>

    suspend fun addTodo(todoDto: TodoDto)

    suspend fun updateTodo(todoDto: TodoDto)

    suspend fun deleteTodo(todoDto: TodoDto)
}