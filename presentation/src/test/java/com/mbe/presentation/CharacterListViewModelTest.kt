package com.mbe.presentation

import app.cash.turbine.test
import com.mbe.domain.character.model.CharacterList
import com.mbe.domain.character.model.CharacterListItem
import com.mbe.domain.character.usecase.GetCharactersUseCase
import com.mbe.domain.common.model.Response
import com.mbe.presentation.character.list.mapper.toModelUI
import com.mbe.presentation.character.list.model.CharacterListFlowState
import com.mbe.presentation.character.list.viewmodel.CharacterListViewModel
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
internal class CharacterListViewModelTest {

    private lateinit var viewModel: CharacterListViewModel
    private val getCharactersUseCase: GetCharactersUseCase = mockk()

    private val defaultCharacterList = CharacterList(
        pages = 42,
        next = true,
        prev = true,
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
        viewModel = CharacterListViewModel(TestDispatcherProvider(), getCharactersUseCase)
    }

    @Test
    fun `request next page success`() = runBlocking {

        // GIVEN
        viewModel.pageNumb = 2
        coEvery { getCharactersUseCase(any()) } returns Response.Success(defaultCharacterList)

        val job = launch {
            viewModel.characterListFlow.test {
                val firstEmission = awaitItem()
                firstEmission.shouldBeInstanceOf<CharacterListFlowState.Loading>()

                val secondEmission = awaitItem()
                secondEmission.shouldBeInstanceOf<CharacterListFlowState.CharacterList>()
                secondEmission.characters shouldBe defaultCharacterList.toModelUI(3)

                cancelAndConsumeRemainingEvents()
            }
        }

        // WHEN
        viewModel.requestNextPage()
        job.join()
        job.cancel()

        // THEN
        viewModel.pageNumb shouldBe 3
    }

    @Test
    fun `request next page error`() = runBlocking {

        // GIVEN
        viewModel.pageNumb = 2
        val errorMessage = "GenericError!"
        coEvery { getCharactersUseCase(any()) } returns Response.Error(Exception(errorMessage))

        val job = launch {
            viewModel.characterListFlow.test {
                val firstEmission = awaitItem()
                firstEmission.shouldBeInstanceOf<CharacterListFlowState.Loading>()

                val secondEmission = awaitItem()
                secondEmission.shouldBeInstanceOf<CharacterListFlowState.Error>()
                secondEmission.message shouldBe errorMessage

                cancelAndConsumeRemainingEvents()
            }
        }

        // WHEN
        viewModel.requestNextPage()
        job.join()
        job.cancel()

        // THEN
        viewModel.pageNumb shouldBe 3
    }

    @Test
    fun `request previous page success`() = runBlocking {

        // GIVEN
        viewModel.pageNumb = 3
        coEvery { getCharactersUseCase(any()) } returns Response.Success(defaultCharacterList)

        val job = launch {
            viewModel.characterListFlow.test {
                val firstEmission = awaitItem()
                firstEmission.shouldBeInstanceOf<CharacterListFlowState.Loading>()

                val secondEmission = awaitItem()
                secondEmission.shouldBeInstanceOf<CharacterListFlowState.CharacterList>()
                secondEmission.characters shouldBe defaultCharacterList.toModelUI(2)

                cancelAndConsumeRemainingEvents()
            }
        }

        // WHEN
        viewModel.requestPreviousPage()
        job.join()
        job.cancel()

        // THEN
        viewModel.pageNumb shouldBe 2
    }

    @Test
    fun `request previous page error`() = runBlocking {

        // GIVEN
        viewModel.pageNumb = 3
        val errorMessage = "GenericError!"
        coEvery { getCharactersUseCase(any()) } returns Response.Error(Exception(errorMessage))

        val job = launch {
            viewModel.characterListFlow.test {
                val firstEmission = awaitItem()
                firstEmission.shouldBeInstanceOf<CharacterListFlowState.Loading>()

                val secondEmission = awaitItem()
                secondEmission.shouldBeInstanceOf<CharacterListFlowState.Error>()
                secondEmission.message shouldBe errorMessage

                cancelAndConsumeRemainingEvents()
            }
        }

        // WHEN
        viewModel.requestPreviousPage()
        job.join()
        job.cancel()

        // THEN
        viewModel.pageNumb shouldBe 2
    }
}
