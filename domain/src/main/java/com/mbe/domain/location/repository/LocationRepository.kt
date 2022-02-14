package com.mbe.domain.location.repository

import com.mbe.domain.common.model.Response
import com.mbe.domain.location.model.Location

abstract class LocationRepository {
    abstract suspend fun getLocation(locationUrl: String): Response<Location>
}