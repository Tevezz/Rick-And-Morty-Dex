package com.mbe.data.character.datasource

import com.apollographql.apollo3.api.ApolloResponse
import com.mbe.data.apollo.CharacterDetailQuery
import com.mbe.data.apollo.CharacterListQuery

internal abstract class CharacterRemoteDataSource {
    abstract suspend fun getCharacter(id: String): ApolloResponse<CharacterDetailQuery.Data>
    abstract suspend fun getCharacters(pageNum: Int): ApolloResponse<CharacterListQuery.Data>
}