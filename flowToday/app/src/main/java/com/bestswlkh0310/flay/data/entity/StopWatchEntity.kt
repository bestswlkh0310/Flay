package com.bestswlkh0310.flay.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bestswlkh0310.flay.data.Tables
import com.bestswlkh0310.flay.domain.model.StopWatchDto

@Entity(tableName = Tables.STOP_WATCH)
data class StopWatchEntity(

    @PrimaryKey(autoGenerate = true)
    var idx: Long = 0,

    val title: String,

    val deadline: String,

    val position: Int
) {
    fun toDto(): StopWatchDto {
        return StopWatchDto(
            idx = idx,
            title = title,
            deadline = deadline,
            position = position
        )
    }
}