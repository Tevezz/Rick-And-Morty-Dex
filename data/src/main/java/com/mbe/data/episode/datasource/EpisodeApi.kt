package com.mbe.data.episode.datasource

import com.mbe.data.episode.model.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface EpisodeApi {
    private companion object {
        const val EPISODE_ENDPOINT = "episode/{ids}"
    }

    @GET(EPISODE_ENDPOINT)
    suspend fun getEpisodes(@Path("ids") ids: List<String>): List<EpisodeResponse>
}