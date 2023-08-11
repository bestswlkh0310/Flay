package com.bestswlkh0310.flay.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bestswlkh0310.flay.data.dao.StopWatchDao
import com.bestswlkh0310.flay.data.dao.TodoDao
import com.bestswlkh0310.flay.data.entity.StopWatchEntity
import com.bestswlkh0310.flay.data.entity.TodoEntity
import com.bestswlkh0310.flay.data.utils.FlayTypeConverter

@Database(
    entities = [
        StopWatchEntity::class,
        TodoEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(FlayTypeConverter::class)
abstract class FlayDatabase : RoomDatabase() {
    abstract fun stopWatchDao(): StopWatchDao
    abstract fun todoDao(): TodoDao
}