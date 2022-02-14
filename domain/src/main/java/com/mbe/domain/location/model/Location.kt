package com.mbe.domain.location.model

data class Location(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val url: String,
    val created: String,
)