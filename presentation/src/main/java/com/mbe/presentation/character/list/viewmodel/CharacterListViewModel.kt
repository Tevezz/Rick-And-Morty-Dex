package com.mbe.presentation.character.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.common.model.Response.Error
import com.mbe.domain.common.model.Response.Success
import com.mbe.presentation.character.dispatcher.DispatcherProvider
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
    private val dispatcherProvider: DispatcherProvider,
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    var pageNumb = 1

    private val _characterListFlow =
        MutableStateFlow<CharacterListFlowState>(CharacterListFlowState.Loading)
    val characterListFlow: StateFlow<CharacterListFlowState> get() = _characterListFlow

    init {
        requestCharactersList(pageNumb)
    }

    fun requestNextPage() {
        pageNumb++
        requestCharactersList(pageNumb)
    }

    fun requestPreviousPage() {
        pageNumb--
        requestCharactersList(pageNumb)
    }

    private fun requestCharactersList(pageNum: Int) {
        viewModelScope.launch(dispatcherProvider.io) {
            _characterListFlow.value = CharacterListFlowState.Loading
            getCharactersUseCase(pageNum).also { response ->
                when (response) {
                    is Success -> {
                        _characterListFlow.value =
                            CharacterListFlowState.CharacterList(response.data.toModelUI(pageNum))
                    }
                    is Error -> {
                        _characterListFlow.value =
                            CharacterListFlowState.Error(response.exception.message.orEmpty())
                    }
                }
            }
        }
    }
}