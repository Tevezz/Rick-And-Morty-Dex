package com.mbe.domain.character.model

import com.mbe.domain.episode.model.Episode
import com.mbe.domain.location.model.Location

data class Character(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episodes: List<Episode>
)