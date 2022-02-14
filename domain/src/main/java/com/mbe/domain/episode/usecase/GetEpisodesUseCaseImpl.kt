package com.mbe.domain.episode.usecase

import com.mbe.domain.common.model.Response
import com.mbe.domain.episode.model.Episode
import com.mbe.domain.episode.repository.EpisodeRepository

class GetEpisodesUseCaseImpl(
    private val repository: EpisodeRepository
): GetEpisodesUseCase() {
    override suspend fun invoke(ids: List<String>): Response<List<Episode>> {
        return repository.getEpisodes(ids)
    }
}