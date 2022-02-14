package com.mbe.data.location.di

import com.mbe.data.location.datasource.LocationRemoteDataSource
import com.mbe.data.location.datasource.LocationRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal object LocationDataModule {

    @Provides
    fun provideLocationRemoteDataSource(
        retrofit: Retrofit
    ): LocationRemoteDataSource {
        return LocationRemoteDataSourceImpl(retrofit)
    }
}