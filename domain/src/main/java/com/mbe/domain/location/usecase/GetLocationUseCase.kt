package com.mbe.domain.location.usecase

import com.mbe.domain.common.model.Response
import com.mbe.domain.location.model.Location

abstract class GetLocationUseCase {
    abstract suspend operator fun invoke(locationUrl: String): Response<Location>
}