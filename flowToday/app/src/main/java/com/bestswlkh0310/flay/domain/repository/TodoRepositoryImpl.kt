package com.bestswlkh0310.flay.domain.repository

import com.bestswlkh0310.flay.data.dao.TodoDao
import com.bestswlkh0310.flay.data.entity.TodoEntity
import com.bestswlkh0310.flay.data.repository.TodoRepository
import com.bestswlkh0310.flay.domain.model.TodoDto
import java.time.LocalDate
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoRepository {
    override suspend fun getTodoList(): List<TodoDto> {
        return todoDao.getAllTodo().map { it.toDto() }
    }

    override suspend fun getTodoByLocalDate(localDate: LocalDate): List<TodoDto> {
        return todoDao.getTodoByLocalDate(localDate).map { it.toDto() }
    }

    override suspend fun addTodo(todoDto: TodoDto) {
        todoDao.insert(todoDto.toEntity())
    }

    override suspend fun updateTodo(todoDto: TodoDto) {
        todoDao.update(todoDto.toEntity())
    }

    override suspend fun deleteTodo(todoDto: TodoDto) {
        todoDao.delete(todoDto.toEntity())
    }
}