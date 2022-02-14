package com.mbe.domain.episode.di

import com.mbe.domain.episode.repository.EpisodeRepository
import com.mbe.domain.episode.usecase.GetEpisodesUseCase
import com.mbe.domain.episode.usecase.GetEpisodesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object EpisodeDomainModule {

    @Provides
    fun provideGetEpisodesUseCase(
        repository: EpisodeRepository
    ): GetEpisodesUseCase {
        return GetEpisodesUseCaseImpl(repository)
    }
}