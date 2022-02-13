package com.mbe.domain.character.repository

import com.mbe.domain.character.model.Character
import com.mbe.domain.common.model.Response

abstract class CharacterRepository {
    abstract suspend fun getCharacters(): Response<List<Character>>
}