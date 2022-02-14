package com.mbe.data.episode.di

import com.mbe.data.episode.datasource.EpisodeRemoteDataSource
import com.mbe.data.episode.datasource.EpisodeRemoteDataSourceImpl
import com.mbe.data.episode.repository.EpisodeRepositoryImpl
import com.mbe.domain.episode.repository.EpisodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal object EpisodeDataModule {

    @Provides
    fun provideEpisodeRemoteDataSource(
        retrofit: Retrofit
    ): EpisodeRemoteDataSource {
        return EpisodeRemoteDataSourceImpl(retrofit)
    }

    @Provides
    fun providesEpisodeRepository(
        dataSource: EpisodeRemoteDataSource
    ): EpisodeRepository {
        return EpisodeRepositoryImpl(dataSource)
    }
}