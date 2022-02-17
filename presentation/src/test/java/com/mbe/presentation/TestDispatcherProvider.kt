package com.mbe.presentation

import com.mbe.presentation.character.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

@ExperimentalCoroutinesApi
internal class TestDispatcherProvider : DispatcherProvider {
    private val testDispatcherProvider = StandardTestDispatcher()
    override val io: CoroutineDispatcher
        get() = testDispatcherProvider
    override val main: CoroutineDispatcher
        get() = testDispatcherProvider
}