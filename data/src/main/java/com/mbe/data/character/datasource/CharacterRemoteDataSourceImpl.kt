package com.mbe.data.character.datasource

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.mbe.data.apollo.CharacterDetailQuery
import com.mbe.data.apollo.CharacterListQuery
import com.mbe.data.character.model.CharacterListResponse
import com.mbe.data.character.model.CharacterResponse
import retrofit2.Retrofit

internal class CharacterRemoteDataSourceImpl(
    retrofit: Retrofit, // TODO REMOVE
    private val apolloClient: ApolloClient
) : CharacterRemoteDataSource() {
    override val api: CharacterApi = retrofit.create(CharacterApi::class.java)

    override suspend fun getCharacter(id: String): ApolloResponse<CharacterDetailQuery.Data> {
        return apolloClient.query(CharacterDetailQuery(id)).execute()
    }

    override suspend fun getCharacters(pageNum: Int): ApolloResponse<CharacterListQuery.Data> {
        return apolloClient.query(CharacterListQuery(pageNum)).execute()
    }
}