package com.mbe.presentation.character.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mbe.domain.character.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val state: SavedStateHandle
) : ViewModel() {

    lateinit var character: Character

    fun testState() {
        state.get<Character>("character")?.also {
            character = it
        }
    }

}