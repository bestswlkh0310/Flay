package com.bestswlkh0310.flay.data.utils;

import androidx.room.TypeConverter;
import java.time.LocalDate

public class FlayTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long): LocalDate? {
        return LocalDate.ofEpochDay(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}
