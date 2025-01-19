package com.dicoding.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Module {
    @Binds
    abstract fun provideArtsUseCase(artsUseCase: ArtsUseCase): IArtsUseCase
}