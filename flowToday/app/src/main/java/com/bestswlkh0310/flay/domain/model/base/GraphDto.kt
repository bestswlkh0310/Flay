package com.bestswlkh0310.flay.domain.model.base

data class GraphDto<D> (
    val x: String,
    val y: Float,
    val data: D
)