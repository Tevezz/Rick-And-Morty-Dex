package com.mbe.presentation.character.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.usecase.GetCharacterDetailUseCase
import com.mbe.domain.common.model.Response
import com.mbe.presentation.character.detail.mapper.toModelUI
import com.mbe.presentation.character.detail.model.CharacterDetailFlowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _characterFlow =
        MutableStateFlow<CharacterDetailFlowState>(CharacterDetailFlowState.Loading)
    val characterFlow: StateFlow<CharacterDetailFlowState> get() = _characterFlow

    init { // TODO There should be a property for this
        state.get<String>("characterId")?.also {
            getCharacterDetails(it)
        }
    }

    private fun getCharacterDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _characterFlow.value = CharacterDetailFlowState.Loading
            getCharacterDetailUseCase(id).also { response ->
                when (response) {
                    is Response.Success -> {
                        _characterFlow.value =
                            CharacterDetailFlowState.CharacterDetail(response.data.toModelUI())
                    }
                    is Response.Error -> {
                        _characterFlow.value =
                            CharacterDetailFlowState.Error(response.exception.message.orEmpty())
                    }
                }
            }
        }
    }
}