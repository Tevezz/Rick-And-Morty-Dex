package com.mbe.data.location.datasource

import com.mbe.data.location.model.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Url

internal interface LocationApi {
    @GET
    suspend fun getLocation(@Url locationUrl: String): LocationResponse
}