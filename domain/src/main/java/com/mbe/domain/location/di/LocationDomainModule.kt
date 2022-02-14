package com.mbe.domain.location.di

import com.mbe.domain.location.repository.LocationRepository
import com.mbe.domain.location.usecase.GetLocationUseCase
import com.mbe.domain.location.usecase.GetLocationUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object LocationDomainModule {

    @Provides
    fun provideGetLocationUseCase(
        repository: LocationRepository
    ): GetLocationUseCase {
        return GetLocationUseCaseImpl(repository)
    }
}