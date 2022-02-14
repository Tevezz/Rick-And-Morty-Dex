package com.mbe.presentation.character.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.common.model.Response.Success
import com.mbe.domain.common.model.Response.Error
import com.mbe.presentation.character.list.mapper.toModelUI
import com.mbe.presentation.character.list.model.CharacterListItemModelUI
import com.mbe.presentation.character.list.model.CharacterListModelUI
import com.mbe.presentation.character.list.ui.CharacterListFragmentDirections
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
    private var characterList: CharacterList = CharacterList(
        count = 0,
        pages = 0,
        next = "String()",
        prev = String(),
        list = emptyList()
    )

    private val _characterListFlow = MutableStateFlow(characterList.toModelUI(1))
    val characterListFlow: StateFlow<CharacterListModelUI> get() = _characterListFlow

    init {
        requestNextPage()
    }

    fun getNavigationAction(characterId: Long): NavDirections {
        val character = characterList.list.first { it.id == characterId }
        return CharacterListFragmentDirections
            .actionCharacterListFragmentToCharacterDetailFragment(character)
    }

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
                        characterList = response.data
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
}