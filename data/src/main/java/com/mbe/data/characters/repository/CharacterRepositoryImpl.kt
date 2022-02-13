package com.mbe.data.characters.repository

import com.mbe.data.characters.datasource.CharacterRemoteDataSource
import com.mbe.data.characters.mapper.toCharacterList
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.repository.CharacterRepository
import com.mbe.domain.common.model.Response

internal class CharacterRepositoryImpl(
    private val dataSource: CharacterRemoteDataSource
) : CharacterRepository() {

    override suspend fun getCharacters(): Response<List<Character>> {
        return try {
            Response.Success(dataSource.getCharacters().toCharacterList())
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}