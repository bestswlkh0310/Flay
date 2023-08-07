package com.bestswlkh0310.flay.data.repository

import com.bestswlkh0310.flay.data.entity.StopWatchEntity
import com.bestswlkh0310.flay.domain.model.StopWatchDto
import com.bestswlkh0310.flay.domain.model.TimeDto

interface StopWatchRepository {
    suspend fun addStopWatch(stopWatchEntity: StopWatchEntity)
    suspend fun getStopWatchList(): List<StopWatchDto>
    suspend fun updateStopWatch(stopWatchDto: StopWatchDto)
}
