package com.mbe.data.character.datasource

import com.mbe.data.character.model.CharacterListResponse

internal abstract class CharacterRemoteDataSource {
    protected abstract val api: CharacterApi
    abstract suspend fun getCharacters(pageNum: Int): CharacterListResponse
}