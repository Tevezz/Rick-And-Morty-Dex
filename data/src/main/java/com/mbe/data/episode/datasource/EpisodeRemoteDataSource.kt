package com.mbe.data.episode.datasource

import com.mbe.data.episode.model.EpisodeResponse

internal abstract class EpisodeRemoteDataSource {
    protected abstract val api: EpisodeApi
    abstract suspend fun getEpisodes(ids: List<String>): List<EpisodeResponse>
}