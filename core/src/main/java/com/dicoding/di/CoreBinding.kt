package com.dicoding.di

import com.dicoding.data.AICRepository
import com.dicoding.domain.ArtsUseCase
import com.dicoding.domain.IAICRepository
import com.dicoding.domain.IArtsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreBinding {
    @Binds
    fun provideArtsUseCase(artsUseCase: ArtsUseCase): IArtsUseCase

    @Binds
    fun provideAICRepository(aicRepository: AICRepository): IAICRepository
}

