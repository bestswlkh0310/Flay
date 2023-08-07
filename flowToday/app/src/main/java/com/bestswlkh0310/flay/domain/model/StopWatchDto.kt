package com.bestswlkh0310.flay.domain.model

import com.bestswlkh0310.flay.data.entity.StopWatchEntity

data class StopWatchDto(
    val idx: Long,
    val title: String,
    val deadline: String
) {
    fun toEntity(): StopWatchEntity {
        return StopWatchEntity(
            idx = idx,
            title = title,
            deadline = deadline
        )
    }
}