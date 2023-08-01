package com.bestswlkh0310.flay.util

import com.bestswlkh0310.flay.dto.TimeDto
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object CalculateRemainingTime {

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
}