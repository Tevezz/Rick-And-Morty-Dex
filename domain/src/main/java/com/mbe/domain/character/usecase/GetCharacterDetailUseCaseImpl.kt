package com.mbe.domain.character.usecase

import com.mbe.domain.character.model.Character
import com.mbe.domain.character.repository.CharacterRepository
import com.mbe.domain.common.model.Response

class GetCharacterDetailUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharacterDetailUseCase() {
    override suspend fun invoke(id: String): Response<Character> {
        return repository.getCharacter(id)
    }
}