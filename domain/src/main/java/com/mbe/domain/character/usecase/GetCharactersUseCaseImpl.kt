package com.mbe.domain.character.usecase

import com.mbe.domain.character.model.Character
import com.mbe.domain.character.repository.CharacterRepository
import com.mbe.domain.common.model.Response
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val repository: CharacterRepository
) : GetCharactersUseCase() {
    override suspend fun invoke(): Response<List<Character>> {
        return repository.getCharacters()
    }
}