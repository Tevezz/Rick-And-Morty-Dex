package com.mbe.data.episode.repository

import com.mbe.data.episode.datasource.EpisodeRemoteDataSource
import com.mbe.data.episode.mapper.toDomainModel
import com.mbe.domain.common.model.Response
import com.mbe.domain.episode.model.Episode
import com.mbe.domain.episode.repository.EpisodeRepository

internal class EpisodeRepositoryImpl(
    private val dataSource: EpisodeRemoteDataSource
) : EpisodeRepository() {

    override suspend fun getEpisodes(ids: List<String>): Response<List<Episode>> {
        return try {
            Response.Success(dataSource.getEpisodes(ids).toDomainModel())
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}