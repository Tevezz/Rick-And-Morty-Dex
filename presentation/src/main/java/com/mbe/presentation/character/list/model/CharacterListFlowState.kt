package com.mbe.presentation.character.list.model

internal sealed class CharacterListFlowState {
    object Loading: CharacterListFlowState()
    data class CharacterList(val characters: CharacterListUI): CharacterListFlowState()
}