package com.mbe.domain.location.usecase

import com.mbe.domain.common.model.Response
import com.mbe.domain.location.exception.InvalidLocationUrlException
import com.mbe.domain.location.model.Location
import com.mbe.domain.location.repository.LocationRepository
import javax.inject.Inject

class GetLocationUseCaseImpl @Inject constructor(
    private val repository: LocationRepository
) : GetLocationUseCase() {

    override suspend fun invoke(locationUrl: String): Response<Location> {
        if (locationUrl.isBlank()) return Response.Error(InvalidLocationUrlException())
        return repository.getLocation(locationUrl)
    }
}