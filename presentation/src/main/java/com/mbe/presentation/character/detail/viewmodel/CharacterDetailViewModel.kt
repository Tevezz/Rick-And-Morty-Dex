package com.mbe.presentation.character.detail.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbe.domain.character.model.Character
import com.mbe.domain.common.model.Response
import com.mbe.domain.location.model.Location
import com.mbe.domain.location.usecase.GetLocationUseCase
import com.mbe.presentation.character.detail.mapper.toModelUI
import com.mbe.presentation.character.detail.model.CharacterLocationModelUI
import com.mbe.presentation.character.detail.model.LocationFlowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val locationUseCase: GetLocationUseCase,
    state: SavedStateHandle
) : ViewModel() {

    lateinit var character: Character

    private val _locationStateFlow = MutableStateFlow<LocationFlowState>(LocationFlowState.Loading)
    val locationStateFlow: StateFlow<LocationFlowState> get() = _locationStateFlow

    init {
        state.get<Character>("character")?.also {
            character = it
        }
    }

    fun getLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            _locationStateFlow.value = LocationFlowState.Loading
            _locationStateFlow.value = LocationFlowState.Location(
                Pair(
                    handleLocationResponse(locationUseCase(character.originUrl)),
                    handleLocationResponse(locationUseCase(character.locationUrl))
                )
            )
        }
    }

    private fun handleLocationResponse(response: Response<Location>): CharacterLocationModelUI {
        return when (response) {
            is Response.Success -> response.data.toModelUI()
            is Response.Error -> CharacterLocationModelUI(String(), String(), String())
        }
    }
}