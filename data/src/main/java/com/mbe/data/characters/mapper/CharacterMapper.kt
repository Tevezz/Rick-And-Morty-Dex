package com.mbe.data.characters.mapper

import com.mbe.data.characters.model.CharacterListResponse
import com.mbe.data.characters.model.CharacterResponse
import com.mbe.data.exception.NoContentException
import com.mbe.domain.character.model.Character

fun CharacterListResponse.toCharacterList(): List<Character> {
    return results?.mapNotNull {
        try {
            it.toCharacter()
        } catch (e: Exception) {
            null
        }
    } ?: emptyList()
}

internal fun CharacterResponse.toCharacter(): Character {
    return Character(
        id = id ?: throw NoContentException(),
        name = name ?: throw NoContentException(),
        status = status ?: throw NoContentException(),
        species = species ?: throw NoContentException(),
        type = type.orEmpty(),
        gender = gender.orEmpty()
    )
}