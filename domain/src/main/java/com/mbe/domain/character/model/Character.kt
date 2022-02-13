package com.mbe.domain.character.model

data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String
)