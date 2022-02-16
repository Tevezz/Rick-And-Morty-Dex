package com.mbe.domain

import com.mbe.domain.character.model.Character
import com.mbe.domain.character.repository.CharacterRepository
import com.mbe.domain.character.usecase.GetCharacterDetailUseCase
import com.mbe.domain.character.usecase.GetCharacterDetailUseCaseImpl
import com.mbe.domain.common.model.Response
import com.mbe.domain.episode.model.Episode
import com.mbe.domain.location.model.Location
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GerCharacterDetailUseCaseTest {

    private val repository: CharacterRepository = mockk()
    private lateinit var useCaseTest: GetCharacterDetailUseCase

    private val character = Character(
        id = "7",
        name = "Abradolf Lincler",
        status = "unknown",
        species = "Human",
        type = "Genetic experiment",
        gender = "Male",
        origin = Location(
            name = "Earth (Replacement Dimension)",
            type = "Planet",
            dimension = "Replacement Dimension"
        ),
        location = Location(
            name = "Testicle Monster Dimension",
            type = "Dimension",
            dimension = "Testicle Monster Dimension"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/7.jpeg",
        episodes = listOf(
            Episode(
                id = "10",
                name = "Close Rick-counters of the Rick Kind",
                airDate = "April 7, 2014",
                episode ="S01E10"
            ),
            Episode(
                id = "11",
                name ="Ricksy Business",
                airDate = "April 14, 2014",
                episode = "S01E11"
            )
        )
    )

    @Before
    fun setup() {
        useCaseTest = GetCharacterDetailUseCaseImpl(repository)
    }

    @Test
    fun `get character detail success`() = runBlocking {

        // GIVEN
        coEvery { repository.getCharacter(any()) } returns Response.Success(character)

        // WHEN
        val result = useCaseTest.invoke("1")

        // THEN
        result.shouldBeInstanceOf<Response.Success<Character>>()
        result.data.episodes.size shouldBe 2
    }

    @Test
    fun `get characters list error`() = runBlocking {

        // GIVEN
        coEvery { repository.getCharacter(any()) } returns
            Response.Error(Exception("GenericError"))

        // WHEN
        val result = useCaseTest.invoke("1")

        // THEN
        result.shouldBeInstanceOf<Response.Error>()
        result.exception.message shouldBe "GenericError"
    }
}