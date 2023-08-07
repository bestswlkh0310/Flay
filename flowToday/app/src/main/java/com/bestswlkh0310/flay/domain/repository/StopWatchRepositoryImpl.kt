package com.bestswlkh0310.flay.domain.repository

import com.bestswlkh0310.flay.data.dao.StopWatchDao
import com.bestswlkh0310.flay.data.entity.StopWatchEntity
import com.bestswlkh0310.flay.data.repository.StopWatchRepository
import com.bestswlkh0310.flay.domain.model.StopWatchDto
import javax.inject.Inject

class StopWatchRepositoryImpl @Inject constructor(
    private val stopWatchDao: StopWatchDao
): StopWatchRepository {
    override suspend fun addStopWatch(stopWatchEntity: StopWatchEntity) {
        stopWatchDao.insert(stopWatchEntity)
    }

    override suspend fun getStopWatchList(): List<StopWatchDto> {
        return stopWatchDao.getAllStopWatch().map { it.toDto() }
    }
}