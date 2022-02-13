package com.mbe.domain.character.usecase

import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.common.model.Response

abstract class GetCharactersUseCase {
    abstract suspend operator fun invoke(pageNum: Int): Response<CharacterList>
}