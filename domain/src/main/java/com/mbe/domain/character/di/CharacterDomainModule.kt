package com.mbe.domain.character.di

import com.mbe.domain.character.repository.CharacterRepository
import com.mbe.domain.character.usecase.GetCharacterDetailUseCase
import com.mbe.domain.character.usecase.GetCharacterDetailUseCaseImpl
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.character.usecase.GetCharactersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CharacterDomainModule {

    @Provides
    fun provideGetCharactersUseCase(
        repository: CharacterRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCaseImpl(repository)
    }

    @Provides
    fun provideGetCharacterDetailUseCase(
        repository: CharacterRepository
    ): GetCharacterDetailUseCase {
        return GetCharacterDetailUseCaseImpl(repository)
    }
}