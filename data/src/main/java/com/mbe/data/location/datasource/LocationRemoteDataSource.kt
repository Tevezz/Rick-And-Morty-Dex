package com.mbe.data.location.datasource

import com.mbe.data.location.model.LocationResponse

internal abstract class LocationRemoteDataSource {
    protected abstract val api: LocationApi
    abstract suspend fun getLocation(locationUrl: String): LocationResponse
}