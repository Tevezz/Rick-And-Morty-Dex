package com.mbe.data.character.datasource

import com.mbe.data.character.model.CharacterListResponse
import retrofit2.Retrofit

class CharacterRemoteDataSourceImpl(
    retrofit: Retrofit
) : CharacterRemoteDataSource() {
    override val api: CharacterApi = retrofit.create(CharacterApi::class.java)

    override suspend fun getCharacters(): CharacterListResponse {
        return api.getCharacters()
    }
}