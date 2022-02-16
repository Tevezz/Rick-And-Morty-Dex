package com.mbe.data.episode.model

internal data class EpisodeResponse(
    val id: String?,
    val name: String?,
    val air_date: String?,
    val episode: String?,
    val characters: List<String>?,
    val url: String?,
    val created: String?
)