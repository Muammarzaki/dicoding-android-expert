package com.dicoding.favorite.di

import com.dicoding.core.domain.IArtsUseCase
import com.dicoding.favorite.ui.viewmodel.FavoriteViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {
    @Provides
    fun provideFavoriteViewModel(artsUseCase: IArtsUseCase): FavoriteViewModel {
        return FavoriteViewModel(artsUseCase)
    }
}