package com.mbe.domain.character.model

data class CharacterList(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String,
    val list: List<Character>
)