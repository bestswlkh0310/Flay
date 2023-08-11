package com.bestswlkh0310.flay.domain.model

import com.bestswlkh0310.flay.data.entity.TodoEntity
import java.time.LocalDate

data class TodoDto(
    val idx: Long,
    val todo: String,
    val createdTime: LocalDate,
    val isDone: Boolean,
    val position: Int
) {
    fun toEntity(): TodoEntity {
        return TodoEntity(
            idx = idx,
            todo = todo,
            createdTime = createdTime,
            isDone = isDone,
            position = position
        )
    }
}
