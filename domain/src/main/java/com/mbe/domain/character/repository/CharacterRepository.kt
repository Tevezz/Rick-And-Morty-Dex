package com.mbe.domain.character.repository

import com.mbe.domain.character.model.Character
import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.common.model.Response

abstract class CharacterRepository {
    abstract suspend fun getCharacter(id: String): Response<Character>
    abstract suspend fun getCharacters(pageNum: Int): Response<CharacterList>
}