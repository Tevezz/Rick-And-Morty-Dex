package com.mbe.data.location.mapper

import com.mbe.data.exception.NoContentException
import com.mbe.data.location.model.LocationResponse
import com.mbe.domain.location.model.Location

fun LocationResponse.toDomainModel(): Location {
    return Location(
        name = name ?: throw NoContentException(),
        type = type.orEmpty(),
        dimension = dimension.orEmpty(),
    )
}