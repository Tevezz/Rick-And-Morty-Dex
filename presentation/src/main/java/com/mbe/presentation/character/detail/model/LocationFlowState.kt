package com.mbe.presentation.character.detail.model

sealed class LocationFlowState {
    object Loading: LocationFlowState()
    data class Location(
        val data: Pair<CharacterLocationModelUI, CharacterLocationModelUI>
    ): LocationFlowState()
}
