package com.mbe.data.character.mapper

import com.mbe.data.apollo.CharacterDetailQuery
import com.mbe.data.apollo.CharacterListQuery
import com.mbe.data.exception.NoContentException
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.character.model.CharacterListItem
import com.mbe.domain.episode.model.Episode
import com.mbe.domain.location.model.Location

internal fun CharacterDetailQuery.Character?.toDomainModel(): Character {
    return this?.let {
        Character(
            id = id ?: throw NoContentException(),
            name = name ?: throw NoContentException(),
            status = status.orEmpty(),
            species = species.orEmpty(),
            type = type.orEmpty(),
            gender = gender.orEmpty(),
            origin = origin.toDomainModel(),
            location = location.toDomainModel(),
            image = image.orEmpty(),
            episodes = episode.toListDomainModel()
        )
    } ?: throw NoContentException()
}

internal fun CharacterDetailQuery.Origin?.toDomainModel(): Location {
    return Location(
        name = this?.name.orEmpty(),
        type = this?.type.orEmpty(),
        dimension = this?.dimension.orEmpty()
    )
}

internal fun CharacterDetailQuery.Location?.toDomainModel(): Location {
    return Location(
        name = this?.name.orEmpty(),
        type = this?.type.orEmpty(),
        dimension = this?.dimension.orEmpty()
    )
}

internal fun List<CharacterDetailQuery.Episode?>.toListDomainModel(): List<Episode> {
    return map {
        Episode(
            id = it?.id.orEmpty(),
            name = it?.name.orEmpty(),
            airDate = it?.air_date.orEmpty(),
            episode = it?.episode.orEmpty()
        )
    }
}

internal fun CharacterListQuery.Characters?.toDomainModel(): CharacterList {
    return this?.let {
        CharacterList(
            pages = info?.pages ?: throw NoContentException(),
            next = info.next?.let { true } ?: false,
            prev = info.prev?.let { true } ?: false,
            list = results?.toDomainModel() ?: emptyList()
        )
    } ?: throw NoContentException()
}

internal fun List<CharacterListQuery.Result?>.toDomainModel(): List<CharacterListItem> {
    return mapNotNull {
        try {
            it?.toDomainModel()
        } catch (e: Exception) {
            null
        }
    }
}

internal fun CharacterListQuery.Result.toDomainModel(): CharacterListItem {
    return CharacterListItem(
        id = id ?: throw NoContentException(),
        name = name ?: throw NoContentException(),
        image = image.orEmpty(),
        status = status.orEmpty(),
        species = species.orEmpty(),
        location = location?.name.orEmpty()
    )
}