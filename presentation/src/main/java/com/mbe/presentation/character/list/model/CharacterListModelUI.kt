package com.mbe.presentation.character.list.model

data class CharacterListModelUI(
    val currentPage: Int,
    val count: Int,
    val pages: Int,
    val hasNext: Boolean,
    val hasPrev: Boolean,
    val list: List<CharacterListItemModelUI>
)