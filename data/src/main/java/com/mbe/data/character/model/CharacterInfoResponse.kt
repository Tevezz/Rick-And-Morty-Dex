package com.mbe.data.character.model

internal data class CharacterInfoResponse(
    val count: Int?,
    val pages: Int?,
    val next: String?,
    val prev: String?
)