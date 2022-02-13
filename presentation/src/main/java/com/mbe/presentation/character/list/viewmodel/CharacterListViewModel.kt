package com.mbe.presentation.character.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.common.model.Response.Success
import com.mbe.domain.common.model.Response.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    fun requestCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersUseCase().also { response ->
                when (response) {
                    is Success -> {
                        Log.e("ViewModel", "Success: ${response.data.size}")
                    }
                    is Error -> {
                        Log.e("ViewModel", "Error: ${response.exception.message}")
                    }
                }
            }
        }
    }
}