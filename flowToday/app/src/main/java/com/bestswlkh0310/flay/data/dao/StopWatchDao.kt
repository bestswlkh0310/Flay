package com.bestswlkh0310.flay.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bestswlkh0310.flay.data.Tables
import com.bestswlkh0310.flay.data.base.BaseDao
import com.bestswlkh0310.flay.data.entity.StopWatchEntity


@Dao
interface StopWatchDao : BaseDao<StopWatchEntity> {

    @Query("SELECT * FROM ${Tables.STOP_WATCH}")
    suspend fun getAllStopWatch(): List<StopWatchEntity>
}