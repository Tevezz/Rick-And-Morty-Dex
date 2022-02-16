package com.mbe.presentation.character.detail.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbe.domain.character.model.Character
import com.mbe.domain.common.model.Response
import com.mbe.domain.episode.usecase.GetEpisodesUseCase
import com.mbe.domain.location.model.Location
import com.mbe.domain.location.usecase.GetLocationUseCase
import com.mbe.presentation.character.detail.mapper.toModelUI
import com.mbe.presentation.character.detail.model.CharacterLocationModelUI
import com.mbe.presentation.character.detail.model.EpisodeFlowState
import com.mbe.presentation.character.detail.model.LocationFlowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val locationUseCase: GetLocationUseCase,
    private val episodesUseCase: GetEpisodesUseCase,
    state: SavedStateHandle
) : ViewModel() {

    lateinit var character: Character

    private val _locationStateFlow = MutableStateFlow<LocationFlowState>(LocationFlowState.Loading)
    val locationStateFlow: StateFlow<LocationFlowState> get() = _locationStateFlow

    private val _episodeStateFlow = MutableStateFlow<EpisodeFlowState>(EpisodeFlowState.Loading)
    val episodeStateFlow: StateFlow<EpisodeFlowState> get() = _episodeStateFlow

    init {
        state.get<Character>("character")?.also {
            character = it
        }
    }

    fun getLocation() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _locationStateFlow.value = LocationFlowState.Loading
//            _locationStateFlow.value = LocationFlowState.Location(
//                Pair(
//                    handleLocationResponse(locationUseCase(character.originUrl)),
//                    handleLocationResponse(locationUseCase(character.locationUrl))
//                )
//            )
//        }
    }

    private fun handleLocationResponse(response: Response<Location>): CharacterLocationModelUI {
        return when (response) {
            is Response.Success -> response.data.toModelUI()
            is Response.Error -> CharacterLocationModelUI(String(), String(), String())
        }
    }

    fun getEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            _episodeStateFlow.value = EpisodeFlowState.Loading
            _episodeStateFlow.value = episodesUseCase(parseCharacterEpisodes()).let { response ->
                when (response) {
                    is Response.Success -> {
                        EpisodeFlowState.Episodes(response.data.toModelUI())
                    }
                    is Response.Error -> {
                        EpisodeFlowState.Error
                    }
                }
            }
        }
    }

    private fun parseCharacterEpisodes(): List<String> {
//        return character.episode.mapNotNull {
//            try {
//                it.split("/").last()
//            } catch (e: Exception) {
//                null
//            }
//        }
        return emptyList()
    }
}