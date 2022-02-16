package com.mbe.domain

import com.mbe.domain.character.exception.InvalidPageNumberException
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.character.model.CharacterListItem
import com.mbe.domain.character.repository.CharacterRepository
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.character.usecase.GetCharactersUseCaseImpl
import com.mbe.domain.common.model.Response
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetCharactersUseCaseTest {

    private val repository: CharacterRepository = mockk()
    private lateinit var useCaseTest: GetCharactersUseCase

    private val successCharacterList = CharacterList(
        pages = 42,
        next = true,
        prev = false,
        list = listOf(
            CharacterListItem(
                id = "21",
                name = "Aqua Morty",
                status = "unknown",
                species = "Humanoid",
                location = "Earth",
                image = "https://rickandmortyapi.com/api/character/avatar/21.jpeg"
            )
        )
    )

    @Before
    fun setup() {
        useCaseTest = GetCharactersUseCaseImpl(repository)
    }

    @Test
    fun `get characters list success`() = runBlocking {

        // GIVEN
        coEvery { repository.getCharacters(any()) } returns Response.Success(successCharacterList)

        // WHEN
        val result = useCaseTest.invoke(1)

        // THEN
        result.shouldBeInstanceOf<Response.Success<CharacterList>>()
        result.data.list.size shouldBe 1
    }

    @Test
    fun `get characters list error`() = runBlocking {

        // GIVEN
        coEvery { repository.getCharacters(any()) } returns
            Response.Error(Exception("GenericError"))

        // WHEN
        val result = useCaseTest.invoke(1)

        // THEN
        result.shouldBeInstanceOf<Response.Error>()
        result.exception.message shouldBe "GenericError"
    }

    @Test
    fun `get characters list page number is zero`() = runBlocking {
        // GIVEN
        val pageNumber = 0

        // WHEN
        val result = useCaseTest(pageNumber)

        // THEN
        result.shouldBeInstanceOf<Response.Error>()
        result.exception.shouldBeInstanceOf<InvalidPageNumberException>()
        result.exception.message shouldBe InvalidPageNumberException().message
    }
}