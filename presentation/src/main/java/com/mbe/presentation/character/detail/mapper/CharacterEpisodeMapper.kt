package com.mbe.presentation.character.detail.mapper

import com.mbe.domain.episode.model.Episode
import com.mbe.presentation.character.detail.model.CharacterEpisodeModelUI

fun List<Episode>.toModelUI(): List<CharacterEpisodeModelUI> {
    return map { it.toModelUI() }
}

fun Episode.toModelUI(): CharacterEpisodeModelUI {
    return CharacterEpisodeModelUI(
        id, name, airDate, episode
    )
}