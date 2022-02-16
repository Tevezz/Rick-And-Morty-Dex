package com.mbe.data.character.di

import com.apollographql.apollo3.ApolloClient
import com.mbe.data.character.datasource.CharacterRemoteDataSource
import com.mbe.data.character.datasource.CharacterRemoteDataSourceImpl
import com.mbe.data.character.repository.CharacterRepositoryImpl
import com.mbe.domain.character.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object CharacterDataModule {

    @Provides
    fun provideCharacterRemoteDataSource(
        apolloClient: ApolloClient
    ): CharacterRemoteDataSource {
        return CharacterRemoteDataSourceImpl(apolloClient)
    }

    @Provides
    fun provideCharacterRepository(
        dataSource: CharacterRemoteDataSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(dataSource)
    }
}