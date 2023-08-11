package com.bestswlkh0310.flay.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bestswlkh0310.flay.data.Tables
import com.bestswlkh0310.flay.domain.model.TodoDto
import java.time.LocalDate

@Entity(tableName = Tables.TODO)
data class TodoEntity(

    @PrimaryKey(autoGenerate = true)
    var idx: Long = 0,

    var todo: String,

    var createdTime: LocalDate,

    var isDone: Boolean
) {

    fun toDto(): TodoDto {
        return TodoDto(
            idx = idx,
            todo = todo,
            createdTime = createdTime,
            isDone = isDone
        )
    }
}