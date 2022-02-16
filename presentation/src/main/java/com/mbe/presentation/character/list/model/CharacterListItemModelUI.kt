package com.mbe.presentation.character.list.model

data class CharacterListItemModelUI(
    val id: String,
    val name: String,
    val image: String,
    val status: CharacterListStatus,
    val species: String,
    val location: String
)