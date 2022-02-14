package com.mbe.presentation.character.detail.model

sealed class EpisodeFlowState {
    object Error: EpisodeFlowState()
    object Loading: EpisodeFlowState()
    data class Episodes(val episodes: List<CharacterEpisodeModelUI>): EpisodeFlowState()
}