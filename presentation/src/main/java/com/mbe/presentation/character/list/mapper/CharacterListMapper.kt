package com.mbe.presentation.character.list.mapper

import com.mbe.domain.character.model.Character
import com.mbe.domain.character.model.CharacterList
import com.mbe.presentation.character.list.model.CharacterListItemModelUI
import com.mbe.presentation.character.list.model.CharacterListModelUI

fun CharacterList.toModelUI(currentPage: Int): CharacterListModelUI {
    return CharacterListModelUI(
        currentPage = currentPage,
        count = count,
        pages = pages,
        hasNext = next.isNotEmpty(),
        hasPrev = prev.isNotEmpty(),
        list = list.toListModelUI()
    )
}

fun List<Character>.toListModelUI(): List<CharacterListItemModelUI> {
    return map { it.toCharacterListModelUI() }
}

fun Character.toCharacterListModelUI(): CharacterListItemModelUI {
    return CharacterListItemModelUI(
        id = id,
        name = name,
        species = species,
        status = status,
        image = image
    )
}