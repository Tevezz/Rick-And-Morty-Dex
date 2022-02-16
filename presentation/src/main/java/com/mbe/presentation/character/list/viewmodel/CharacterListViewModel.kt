package com.mbe.presentation.character.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.common.model.Response.Success
import com.mbe.domain.common.model.Response.Error
import com.mbe.presentation.character.list.mapper.toModelUI
import com.mbe.presentation.character.list.model.CharacterListFlowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private var pageNumb = 1

    private val _characterListFlow =
        MutableStateFlow<CharacterListFlowState>(CharacterListFlowState.Loading)
    val characterListFlow: StateFlow<CharacterListFlowState> get() = _characterListFlow

    init {
        requestCharactersList(1)
    }

//    fun getNavigationAction(characterId: Long): NavDirections {
//        val character = characterList.list.first { it.id == characterId }
//        return CharacterListFragmentDirections
//            .actionCharacterListFragmentToCharacterDetailFragment(character)
//    }

    fun requestNextPage() {
        val value = (_characterListFlow.value as? CharacterListFlowState.CharacterList)?.characters?.hasNext
        if (value != null && value) {
            pageNumb++
            requestCharactersList(pageNumb)
        }
    }

    fun requestPreviousPage() {
        val value = (_characterListFlow.value as? CharacterListFlowState.CharacterList)?.characters?.hasPrev
        if (value != null && value) {
            pageNumb--
            requestCharactersList(pageNumb)
        }
    }

    private fun requestCharactersList(pageNum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersUseCase(pageNum).also { response ->
                when (response) {
                    is Success -> {
                        _characterListFlow.value =
                            CharacterListFlowState.CharacterList(response.data.toModelUI(pageNum))
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