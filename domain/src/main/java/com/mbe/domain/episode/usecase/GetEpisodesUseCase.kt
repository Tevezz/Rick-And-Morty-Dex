package com.mbe.domain.episode.usecase

import com.mbe.domain.common.model.Response
import com.mbe.domain.episode.model.Episode

abstract class GetEpisodesUseCase {
    abstract suspend operator fun invoke(ids: List<String>): Response<List<Episode>>
}