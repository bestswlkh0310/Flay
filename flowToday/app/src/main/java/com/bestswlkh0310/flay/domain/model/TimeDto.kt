package com.bestswlkh0310.flay.domain.model

import java.time.LocalDateTime

data class TimeDto(
    val totalDay: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val hour: Int,
    val minute: Int,
    val second: Int
)