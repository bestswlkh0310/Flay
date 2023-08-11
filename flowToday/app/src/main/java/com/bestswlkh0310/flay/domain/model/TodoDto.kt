package com.bestswlkh0310.flay.domain.model

import java.time.LocalDate

data class TodoDto(
    val idx: Long,
    val todo: String,
    val createdTime: LocalDate,
    val done: Boolean
)
