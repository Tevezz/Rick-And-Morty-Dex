package com.mbe.presentation.character.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.common.model.Response.Success
import com.mbe.domain.common.model.Response.Error
import com.mbe.presentation.character.list.mapper.toModelUI
import com.mbe.presentation.character.list.model.CharacterListItemModelUI
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

    private var pageNumb = 0

    private val _characterListFlow = MutableStateFlow<CharacterListModelUI>(dummyFlow())
    val characterList: StateFlow<CharacterListModelUI> get() = _characterListFlow

    fun requestNextPage() {
        if (_characterListFlow.value.hasNext) {
            pageNumb++
            requestCharactersList(pageNumb)
        }
    }

    fun requestPreviousPage() {
        if (_characterListFlow.value.hasPrev) {
            pageNumb--
            requestCharactersList(pageNumb)
        }
    }

    private fun requestCharactersList(pageNum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersUseCase(pageNum).also { response ->
                when (response) {
                    is Success -> {
                        _characterListFlow.value = response.data.toModelUI(pageNum)
                    }
                    is Error -> {
                        // TODO Handle Error
                        Log.e("ViewModel", "Error: ${response.exception.message}")
                    }
                }
            }
        }
    }

    private fun dummyFlow(): CharacterListModelUI = CharacterListModelUI(
        currentPage = 0,
        count = 0,
        pages = 0,
        hasNext = true,
        hasPrev = false,
        list = emptyList()
    )
}