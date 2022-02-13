package com.mbe.data.characters.di

import com.mbe.data.characters.datasource.CharacterRemoteDataSource
import com.mbe.data.characters.datasource.CharacterRemoteDataSourceImpl
import com.mbe.data.characters.repository.CharacterRepositoryImpl
import com.mbe.domain.character.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CharacterDataModule {

    @Provides
    fun provideCharacterRemoteDataSource(
        retrofit: Retrofit
    ): CharacterRemoteDataSource {
        return CharacterRemoteDataSourceImpl(retrofit)
    }

    @Provides
    fun provideCharacterRepository(
        dataSource: CharacterRemoteDataSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(dataSource)
    }
}