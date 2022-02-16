package com.mbe.presentation.character.detail.model

sealed class CharacterDetailFlowState {
    object Loading: CharacterDetailFlowState()
    data class Error(val message: String): CharacterDetailFlowState()
    data class CharacterDetail(val character: CharacterDetailUI): CharacterDetailFlowState()
}