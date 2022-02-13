package com.mbe.data.character.model

internal data class CharacterResponse(
    val id: Long?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val image: String?
    // TODO OTHER PARTS
)