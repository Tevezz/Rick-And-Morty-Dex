package com.mbe.presentation.character.list.mapper

import com.mbe.domain.character.model.Character
import com.mbe.presentation.character.list.model.CharacterListModelUI

fun List<Character>.toListModelUI(): List<CharacterListModelUI> {
    return map { it.toCharacterListModelUI() }
}

fun Character.toCharacterListModelUI(): CharacterListModelUI {
    return CharacterListModelUI(
        id = id,
        name = name,
        species = species,
        status = status,
        image = image
    )
}