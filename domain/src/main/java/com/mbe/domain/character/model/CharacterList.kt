package com.mbe.domain.character.model

data class CharacterList(
    val pages: Int,
    val next: Boolean,
    val prev: Boolean,
    val list: List<CharacterListItem>
)