package com.mbe.presentation.character.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.common.model.Response.Success
import com.mbe.domain.common.model.Response.Error
import com.mbe.presentation.character.list.mapper.toListModelUI
import com.mbe.presentation.character.list.model.CharacterListModelUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characterListFlow = MutableStateFlow<List<CharacterListModelUI>>(emptyList())
    val characterList: StateFlow<List<CharacterListModelUI>> get() = _characterListFlow

    fun requestCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersUseCase().also { response ->
                when (response) {
                    is Success -> {
                        _characterListFlow.value = response.data.toListModelUI()
                        Log.e("ViewModel", "Success: ${response.data.size}")
                    }
                    is Error -> {
                        // TODO Handle Error
                        Log.e("ViewModel", "Error: ${response.exception.message}")
                    }
                }
            }
        }
    }
}