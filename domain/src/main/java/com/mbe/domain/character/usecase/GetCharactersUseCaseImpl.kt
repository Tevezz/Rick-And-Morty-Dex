package com.mbe.domain.character.usecase

import com.mbe.domain.character.exception.InvalidPageNumberException
import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.character.repository.CharacterRepository
import com.mbe.domain.common.model.Response
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val repository: CharacterRepository
) : GetCharactersUseCase() {
    override suspend fun invoke(pageNum: Int): Response<CharacterList> {
        if (pageNum < 1) return Response.Error(InvalidPageNumberException())
        return repository.getCharacters(pageNum)
    }
}