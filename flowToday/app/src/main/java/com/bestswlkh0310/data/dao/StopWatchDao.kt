package com.bestswlkh0310.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.bestswlkh0310.data.Tables
import com.bestswlkh0310.data.base.BaseDao
import com.bestswlkh0310.data.entity.StopWatchEntity


@Dao
interface StopWatchDao : BaseDao<StopWatchEntity> {

    @Query("SELECT * FROM ${Tables.STOP_WATCH}")
    suspend fun getAllStopWatch(): List<StopWatchEntity>

    @Query("INSERT INTO ${Tables.STOP_WATCH}")
    override suspend fun insert(entity: StopWatchEntity)
}
