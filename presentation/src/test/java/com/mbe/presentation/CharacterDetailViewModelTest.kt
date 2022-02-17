package com.mbe.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.mbe.domain.character.model.Character
import com.mbe.domain.character.usecase.GetCharacterDetailUseCase
import com.mbe.domain.common.model.Response
import com.mbe.domain.episode.model.Episode
import com.mbe.domain.location.model.Location
import com.mbe.presentation.character.detail.mapper.toModelUI
import com.mbe.presentation.character.detail.model.CharacterDetailFlowState
import com.mbe.presentation.character.detail.viewmodel.CharacterDetailViewModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
internal class CharacterDetailViewModelTest {

    private lateinit var viewModel: CharacterDetailViewModel
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase = mockk()
    private val stateHandle: SavedStateHandle = mockk()

    private val characterId = "1"
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
        coEvery { stateHandle.get<String>(any()) } returns characterId
        viewModel = CharacterDetailViewModel(
            stateHandle,
            TestDispatcherProvider(),
            getCharacterDetailUseCase
        )
    }

    @Test
    fun `get character details success`() = runBlocking {

        // GIVEN
        coEvery { getCharacterDetailUseCase(any()) } returns Response.Success(character)

        val job = launch {
            viewModel.characterFlow.test {
                val firstEmission = awaitItem()
                firstEmission.shouldBeInstanceOf<CharacterDetailFlowState.Loading>()

                val secondEmission = awaitItem()
                secondEmission.shouldBeInstanceOf<CharacterDetailFlowState.CharacterDetail>()
                secondEmission.character shouldBe character.toModelUI()

                cancelAndConsumeRemainingEvents()
            }
        }

        // WHEN
        viewModel.getCharacterDetails(characterId)
        job.join()
        job.cancel()

        // THEN
    }

    @Test
    fun `get character details error`() = runBlocking {

        // GIVEN
        val errorMessage = "GenericError!"
        coEvery { getCharacterDetailUseCase(any()) } returns Response.Error(Exception(errorMessage))

        val job = launch {
            viewModel.characterFlow.test {
                val firstEmission = awaitItem()
                firstEmission.shouldBeInstanceOf<CharacterDetailFlowState.Loading>()

                val secondEmission = awaitItem()
                secondEmission.shouldBeInstanceOf<CharacterDetailFlowState.Error>()
                secondEmission.message shouldBe errorMessage

                cancelAndConsumeRemainingEvents()
            }
        }

        // WHEN
        viewModel.getCharacterDetails(characterId)
        job.join()
        job.cancel()

        // THEN
    }
}