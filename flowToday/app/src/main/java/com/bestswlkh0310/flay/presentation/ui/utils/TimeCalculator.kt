package com.bestswlkh0310.flay.presentation.ui.utils

import com.bestswlkh0310.flay.domain.model.TimeDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object TimeCalculator {

    fun calculateRemainingTime(targetDateString: String): TimeDto {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val targetDateTime = LocalDateTime.parse(targetDateString, formatter)
        val currentDateTime = LocalDateTime.now()

        val timeDifference = ChronoUnit.SECONDS.between(currentDateTime, targetDateTime)

        val totalMinutes = timeDifference / 60
        val totalHours = totalMinutes / 60
        val totalDays = totalHours / 24
        val years = totalDays / 365
        val months = (totalDays % 365) / 30
        val days = totalDays % 30

        val remainingSeconds = timeDifference % 60
        val remainingMinutes = totalMinutes % 60
        val remainingHours = totalHours % 24

        return TimeDto(
            totalDays.toInt(),
            years.toInt(),
            months.toInt(),
            days.toInt(),
            remainingHours.toInt(),
            remainingMinutes.toInt(),
            remainingSeconds.toInt()
        )
    }

    fun localDateTimeToString(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return localDateTime.format(formatter)
    }

    fun stringToLocalDateTime(dateTimeString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(dateTimeString, formatter)
    }

    fun isTimeOver(timeDto: TimeDto): Boolean {
        with(timeDto) {
            return totalDay < 0 || year < 0 || day < 0 || month < 0 || day < 0 || minute < 0 || second < 0
        }
    }
}