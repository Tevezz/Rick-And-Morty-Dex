package com.mbe.data.character.model

internal data class CharacterListResponse(
    val info: CharacterInfoResponse?,
    val results: List<CharacterResponse>?
)