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
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal object CharacterDataModule {

    @Provides
    fun provideCharacterRemoteDataSource(
        retrofit: Retrofit,
        apolloClient: ApolloClient
    ): CharacterRemoteDataSource {
        return CharacterRemoteDataSourceImpl(retrofit, apolloClient)
    }

    @Provides
    fun provideCharacterRepository(
        dataSource: CharacterRemoteDataSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(dataSource)
    }
}