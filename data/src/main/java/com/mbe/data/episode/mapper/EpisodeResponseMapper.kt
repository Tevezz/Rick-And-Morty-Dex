package com.mbe.data.episode.mapper

import com.mbe.data.episode.model.EpisodeResponse
import com.mbe.data.exception.NoContentException
import com.mbe.domain.episode.model.Episode

internal fun List<EpisodeResponse>.toDomainModel(): List<Episode> {
    return mapNotNull {
        try {
            it.toDomainModel()
        } catch (e: Exception) {
            null
        }
    }
}

internal fun EpisodeResponse.toDomainModel(): Episode {
    return Episode(
        id = id ?: throw NoContentException(),
        name = name ?: throw NoContentException(),
        airDate = air_date.orEmpty(),
        episode = episode.orEmpty(),
        characters = characters.orEmpty(),
        url = url.orEmpty(),
        created = created.orEmpty()
    )
}