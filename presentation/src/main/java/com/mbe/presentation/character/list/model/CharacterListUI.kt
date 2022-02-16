package com.mbe.presentation.character.list.model

data class CharacterListUI(
    val currentPage: Int,
    val pages: Int,
    val hasNext: Boolean,
    val hasPrev: Boolean,
    val list: List<CharacterListItemModelUI>
)