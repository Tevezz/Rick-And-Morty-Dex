package com.mbe.data.character.repository

import com.mbe.data.character.datasource.CharacterRemoteDataSource
import com.mbe.data.character.mapper.toCharacterList
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.character.repository.CharacterRepository
import com.mbe.domain.common.model.Response

internal class CharacterRepositoryImpl(
    private val dataSource: CharacterRemoteDataSource
) : CharacterRepository() {

    override suspend fun getCharacters(pageNum: Int): Response<CharacterList> {
        return try {
            Response.Success(dataSource.getCharacters(pageNum).toCharacterList())
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}