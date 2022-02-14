package com.mbe.presentation.character.detail.mapper

import com.mbe.domain.location.model.Location
import com.mbe.presentation.character.detail.model.CharacterLocationModelUI

fun Location.toModelUI(): CharacterLocationModelUI {
    return CharacterLocationModelUI(
        name, type, dimension
    )
}