package com.mbe.data.character.mapper

import com.mbe.data.character.model.CharacterListResponse
import com.mbe.data.character.model.CharacterResponse
import com.mbe.data.exception.NoContentException
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.model.CharacterList

internal fun CharacterListResponse.toCharacterList(): CharacterList {
    return CharacterList(
        count = info?.count ?: throw NoContentException(),
        pages = info.pages ?: throw NoContentException(),
        next = info.next.orEmpty(),
        prev = info.prev.orEmpty(),
        list = results?.toListCharacter() ?: emptyList()
    )
}

internal fun List<CharacterResponse>.toListCharacter(): List<Character> {
    return mapNotNull {
        try {
            it.toCharacter()
        } catch (e: Exception) {
            null
        }
    }
}

internal fun CharacterResponse.toCharacter(): Character {
    return Character(
        id = id ?: throw NoContentException(),
        name = name ?: throw NoContentException(),
        status = status ?: throw NoContentException(),
        species = species ?: throw NoContentException(),
        type = type.orEmpty(),
        gender = gender.orEmpty(),
        image = image ?: throw NoContentException()
    )
}