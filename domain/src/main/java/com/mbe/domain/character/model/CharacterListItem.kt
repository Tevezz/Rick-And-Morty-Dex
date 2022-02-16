package com.mbe.domain.character.model

data class CharacterListItem(
    val id: String,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val location: String
)