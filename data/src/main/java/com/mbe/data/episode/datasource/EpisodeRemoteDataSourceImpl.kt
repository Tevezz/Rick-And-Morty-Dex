package com.mbe.data.episode.datasource

import com.mbe.data.episode.model.EpisodeResponse
import retrofit2.Retrofit

internal class EpisodeRemoteDataSourceImpl(
    retrofit: Retrofit
) : EpisodeRemoteDataSource() {
    override val api: EpisodeApi = retrofit.create(EpisodeApi::class.java)

    override suspend fun getEpisodes(ids: List<String>): List<EpisodeResponse> {
        return api.getEpisodes(ids)
    }
}