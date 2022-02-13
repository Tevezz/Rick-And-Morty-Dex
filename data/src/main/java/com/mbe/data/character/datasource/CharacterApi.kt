package com.mbe.data.character.datasource

import com.mbe.data.character.model.CharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CharacterApi {
    private companion object {
        const val CHARACTER_ENDPOINT = "character"
        const val CHARACTER_PAGE_NUM = "page"
    }
    @GET(CHARACTER_ENDPOINT)
    suspend fun getCharacters(@Query(CHARACTER_PAGE_NUM) pageNum: Int): CharacterListResponse
}