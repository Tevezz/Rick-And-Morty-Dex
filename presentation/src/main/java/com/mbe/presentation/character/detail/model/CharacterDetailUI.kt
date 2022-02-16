package com.mbe.presentation.character.detail.model

data class CharacterDetailUI(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterDetailLocationUI,
    val location: CharacterDetailLocationUI,
    val image: String,
    val episodes: List<CharacterDetailEpisodeUI>
)