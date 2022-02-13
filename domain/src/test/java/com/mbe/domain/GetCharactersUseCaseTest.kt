package com.mbe.domain

import com.mbe.domain.character.exception.InvalidPageNumberException
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.model.CharacterList
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
        count = 826,
        pages = 42,
        next = "https://rickandmortyapi.com/api/character?page=3",
        prev = "https://rickandmortyapi.com/api/character?page=1",
        list = listOf(
            Character(
                id = 21,
                name = "Aqua Morty",
                status = "unknown",
                species = "Humanoid",
                type = "Fish-Person",
                gender = "Male",
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
        with(result.data) {
            count shouldBe successCharacterList.count
            pages shouldBe successCharacterList.pages
            next shouldBe successCharacterList.next
            prev shouldBe successCharacterList.prev
            list.size shouldBe 1
        }
        with(result.data.list.first()) {
            id shouldBe 21
            name shouldBe "Aqua Morty"
            status shouldBe "unknown"
            species shouldBe "Humanoid"
            type shouldBe "Fish-Person"
            gender shouldBe "Male"
            image shouldBe "https://rickandmortyapi.com/api/character/avatar/21.jpeg"
        }
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