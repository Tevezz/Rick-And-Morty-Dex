package com.mbe.data.location.repository

import com.mbe.data.location.datasource.LocationRemoteDataSource
import com.mbe.data.location.mapper.toDomainModel
import com.mbe.domain.common.model.Response
import com.mbe.domain.location.model.Location
import com.mbe.domain.location.repository.LocationRepository

internal class LocationRepositoryImpl(
    private val dataSource: LocationRemoteDataSource
) : LocationRepository() {

    override suspend fun getLocation(locationUrl: String): Response<Location> {
        return try {
            Response.Success(dataSource.getLocation(locationUrl).toDomainModel())
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}