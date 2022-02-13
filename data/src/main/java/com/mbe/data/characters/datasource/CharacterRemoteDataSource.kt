package com.mbe.data.characters.datasource

import com.mbe.data.characters.model.CharacterListResponse

abstract class CharacterRemoteDataSource {
    protected abstract val api: CharacterApi
    abstract suspend fun getCharacters(): CharacterListResponse
}