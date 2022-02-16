package com.mbe.presentation.character.list.mapper

import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.character.model.CharacterListItem
import com.mbe.presentation.character.list.model.CharacterListItemModelUI
import com.mbe.presentation.character.list.model.CharacterListStatus
import com.mbe.presentation.character.list.model.CharacterListUI
import com.mbe.presentation.extension.safeValueOf

fun CharacterList.toModelUI(currentPage: Int): CharacterListUI {
    return CharacterListUI(
        currentPage = currentPage,
        pages = pages,
        hasNext = next,
        hasPrev = prev,
        list = list.toListModelUI()
    )
}

fun List<CharacterListItem>.toListModelUI(): List<CharacterListItemModelUI> {
    return map { it.toModelUI() }
}

fun CharacterListItem.toModelUI(): CharacterListItemModelUI {
    return CharacterListItemModelUI(
        id = id,
        name = name,
        image = image,
        status = safeValueOf(status, CharacterListStatus.Unknown),
        species = species,
        location = location
    )
}