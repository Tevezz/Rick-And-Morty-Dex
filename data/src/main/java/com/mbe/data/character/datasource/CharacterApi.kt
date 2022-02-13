package com.mbe.data.character.datasource

import com.mbe.data.character.model.CharacterListResponse
import retrofit2.http.GET

interface CharacterApi {
    private companion object {
        const val CHARACTER_ENDPOINT = "character"
    }
    @GET(CHARACTER_ENDPOINT)
    suspend fun getCharacters(): CharacterListResponse
}