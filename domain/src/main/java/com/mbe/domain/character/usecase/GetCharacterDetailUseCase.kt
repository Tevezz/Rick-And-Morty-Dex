package com.mbe.domain.character.usecase

import com.mbe.domain.character.model.Character
import com.mbe.domain.common.model.Response

abstract class GetCharacterDetailUseCase {
    abstract suspend operator fun invoke(id: String): Response<Character>
}