package com.mbe.domain.episode.repository

import com.mbe.domain.common.model.Response
import com.mbe.domain.episode.model.Episode

abstract class EpisodeRepository {
    abstract suspend fun getEpisodes(ids: List<String>): Response<List<Episode>>
}