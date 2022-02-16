package com.mbe.presentation.character.detail.mapper

import com.mbe.domain.character.model.Character
import com.mbe.domain.episode.model.Episode
import com.mbe.domain.location.model.Location
import com.mbe.presentation.character.detail.model.CharacterDetailEpisodeUI
import com.mbe.presentation.character.detail.model.CharacterDetailLocationUI
import com.mbe.presentation.character.detail.model.CharacterDetailUI

internal fun Character.toModelUI(): CharacterDetailUI {
    return CharacterDetailUI(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toModelUI(),
        location = location.toModelUI(),
        image = image,
        episodes = episodes.toModelUI()
    )
}

internal fun Location.toModelUI(): CharacterDetailLocationUI {
    return CharacterDetailLocationUI(
        name = name,
        type = type,
        dimension = dimension
    )
}

internal fun List<Episode>.toModelUI(): List<CharacterDetailEpisodeUI> {
    return map { it.toModelUI() }
}

internal fun Episode.toModelUI(): CharacterDetailEpisodeUI {
    return CharacterDetailEpisodeUI(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode
    )
}