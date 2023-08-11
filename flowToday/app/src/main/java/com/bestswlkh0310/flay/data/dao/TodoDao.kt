package com.bestswlkh0310.flay.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.bestswlkh0310.flay.data.Tables
import com.bestswlkh0310.flay.data.base.BaseDao
import com.bestswlkh0310.flay.data.entity.TodoEntity
import java.time.LocalDate
import java.time.LocalDateTime


@Dao
interface TodoDao : BaseDao<TodoEntity> {

    @Query("SELECT * FROM ${Tables.TODO}")
    suspend fun getAllTodo(): List<TodoEntity>


    @Query("SELECT * FROM ${Tables.TODO} WHERE createdTime = :localDate")
    suspend fun getTodoByLocalDate(localDate: LocalDate): List<TodoEntity>
}