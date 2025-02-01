package com.dicoding.core.di

import com.dicoding.core.data.AICRepository
import com.dicoding.core.domain.ArtsUseCase
import com.dicoding.core.domain.IAICRepository
import com.dicoding.core.domain.IArtsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreBinding {
    @Binds
    fun provideArtsUseCase(artsUseCase: ArtsUseCase): IArtsUseCase

    @Binds
    fun provideAICRepository(aicRepository: AICRepository): IAICRepository
}

