package com.mbe.data

import com.mbe.data.character.mapper.toCharacter
import com.mbe.data.character.mapper.toCharacterList
import com.mbe.data.character.mapper.toListCharacter
import com.mbe.data.character.model.CharacterInfoResponse
import com.mbe.data.character.model.CharacterListResponse
import com.mbe.data.character.model.CharacterResponse
import com.mbe.data.exception.NoContentException
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.model.CharacterList
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterResponseMapperTest {

    private val defaultCharacterResponse = CharacterResponse(
        id = 21,
        name = "Aqua Morty",
        status = "unknown",
        species = "Humanoid",
        type = "Fish-Person",
        gender = "Male",
        image = "https://rickandmortyapi.com/api/character/avatar/21.jpeg"
    )

    private val defaultCharacterInfo = CharacterInfoResponse(
        count = 826,
        pages = 42,
        next = "https://rickandmortyapi.com/api/character?page=3",
        prev = "https://rickandmortyapi.com/api/character?page=1"
    )

    private val defaultCharacterResults = listOf(
        defaultCharacterResponse,
        CharacterResponse(
            id = 22,
            name = "Aqua Rick",
            status = "unknown",
            species = "Humanoid",
            type = "Fish-Person",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/22.jpeg"
        ),
        CharacterResponse(
            id = 23,
            name = "Arcade Alien",
            status = "unknown",
            species = "Alien",
            type = String(),
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/23.jpeg"
        )
    )

    private val defaultCharacterListResponse = CharacterListResponse(
        info = defaultCharacterInfo,
        results = defaultCharacterResults
    )

    @Test
    fun `map character list response success`() = runBlocking {
        // GIVEN
        val toBeMapped = defaultCharacterListResponse

        // WHEN
        val result = toBeMapped.toCharacterList()

        // THEN
        result.shouldBeInstanceOf<CharacterList>()
        with(result) {
            count shouldBe defaultCharacterListResponse.info?.count
            pages shouldBe defaultCharacterListResponse.info?.pages
            next shouldBe defaultCharacterListResponse.info?.next
            prev shouldBe defaultCharacterListResponse.info?.prev
        }
        result.list.size shouldBe defaultCharacterListResponse.results?.size
        with(result.list.first()) {
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
    fun `map character list response null info`() {
        // GIVEN
        val toBeMapped = defaultCharacterListResponse.copy(
            info = null
        )

        // WHEN
        var exception: Exception? = null
        try {
            toBeMapped.toCharacterList()
        } catch (e: Exception) {
            exception = e
        }

        // THEN
        exception.shouldBeInstanceOf<NoContentException>()
    }

    @Test
    fun `map character list response info count null`() {
        // GIVEN
        val toBeMapped = defaultCharacterListResponse.copy(
            info = defaultCharacterInfo.copy(
                count = null
            )
        )

        // WHEN
        var exception: Exception? = null
        try {
            toBeMapped.toCharacterList()
        } catch (e: Exception) {
            exception = e
        }

        // THEN
        exception.shouldBeInstanceOf<NoContentException>()
    }

    @Test
    fun `map character list response info pages null`() {
        // GIVEN
        val toBeMapped = defaultCharacterListResponse.copy(
            info = defaultCharacterInfo.copy(
                pages = null
            )
        )

        // WHEN
        var exception: Exception? = null
        try {
            toBeMapped.toCharacterList()
        } catch (e: Exception) {
            exception = e
        }

        // THEN
        exception.shouldBeInstanceOf<NoContentException>()
    }

    @Test
    fun `map character list response results null`() {
        // GIVEN
        val toBeMapped = defaultCharacterListResponse.copy(
            results = null
        )

        // WHEN
        val result = toBeMapped.toCharacterList()

        // THEN
        result.shouldBeInstanceOf<CharacterList>()
        with(result) {
            count shouldBe defaultCharacterListResponse.info?.count
            pages shouldBe defaultCharacterListResponse.info?.pages
            next shouldBe defaultCharacterListResponse.info?.next
            prev shouldBe defaultCharacterListResponse.info?.prev
        }
        result.list.size shouldBe 0
    }

    @Test
    fun `map character list response results two invalid characters`() {
        // GIVEN
        val toBeMapped = listOf(
            defaultCharacterResults.first().copy(
                id = null
            ),
            defaultCharacterResults[1].copy(),
            defaultCharacterResults.last().copy(
                id = null
            )
        )

        // WHEN
        val result = toBeMapped.toListCharacter()

        // THEN
        result.shouldBeInstanceOf<List<Character>>()
        result.size shouldBe 1
        result.first().id shouldBe 22
    }

    @Test
    fun `map character response id null`() = runBlocking {
        // GIVEN
        val toBeMapped = defaultCharacterResponse.copy(
            id = null
        )

        // WHEN
        var exception: Exception? = null
        try {
            toBeMapped.toCharacter()
        } catch (e: Exception) {
            exception = e
        }

        // THEN
        exception.shouldBeInstanceOf<NoContentException>()
        exception shouldNotBe null
    }

    @Test
    fun `map character response name null`() = runBlocking {
        // GIVEN
        val toBeMapped = defaultCharacterResponse.copy(
            name = null
        )

        // WHEN
        var exception: Exception? = null
        try {
            toBeMapped.toCharacter()
        } catch (e: Exception) {
            exception = e
        }

        // THEN
        exception.shouldBeInstanceOf<NoContentException>()
        exception shouldNotBe null
    }

    @Test
    fun `map character response status null`() = runBlocking {
        // GIVEN
        val toBeMapped = defaultCharacterResponse.copy(
            status = null
        )

        // WHEN
        var exception: Exception? = null
        try {
            toBeMapped.toCharacter()
        } catch (e: Exception) {
            exception = e
        }

        // THEN
        exception.shouldBeInstanceOf<NoContentException>()
        exception shouldNotBe null
    }

    @Test
    fun `map character response species null`() = runBlocking {
        // GIVEN
        val toBeMapped = defaultCharacterResponse.copy(
            species = null
        )

        // WHEN
        var exception: Exception? = null
        try {
            toBeMapped.toCharacter()
        } catch (e: Exception) {
            exception = e
        }

        // THEN
        exception.shouldBeInstanceOf<NoContentException>()
        exception shouldNotBe null
    }

    @Test
    fun `map character response image null`() = runBlocking {
        // GIVEN
        val toBeMapped = defaultCharacterResponse.copy(
            image = null
        )

        // WHEN
        var exception: Exception? = null
        try {
            toBeMapped.toCharacter()
        } catch (e: Exception) {
            exception = e
        }

        // THEN
        exception.shouldBeInstanceOf<NoContentException>()
        exception shouldNotBe null
    }
}