package com.mbe.data.location.datasource

import com.mbe.data.location.model.LocationResponse
import retrofit2.Retrofit

internal class LocationRemoteDataSourceImpl(
    retrofit: Retrofit
) : LocationRemoteDataSource() {
    override val api: LocationApi = retrofit.create(LocationApi::class.java)

    override suspend fun getLocation(locationUrl: String): LocationResponse {
        return api.getLocation(locationUrl)
    }

}