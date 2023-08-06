package com.bestswlkh0310.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bestswlkh0310.data.dao.StopWatchDao
import com.bestswlkh0310.data.entity.StopWatchEntity

@Database(
    entities = [
        StopWatchEntity::class
    ],
    version = 10,
    exportSchema = false
)

abstract class FlayDatabase : RoomDatabase() {
    abstract fun stopWatchDao(): StopWatchDao
}