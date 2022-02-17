package com.mbe.presentation.character.di

import com.mbe.presentation.character.dispatcher.DispatcherProvider
import com.mbe.presentation.character.dispatcher.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object CharacterPresentationModule {

    @Provides
    fun provideDispatcher(): DispatcherProvider {
        return DispatcherProviderImpl()
    }
}