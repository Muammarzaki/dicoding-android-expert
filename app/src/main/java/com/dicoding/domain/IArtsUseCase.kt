package com.dicoding.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface IArtsUseCase {
    fun getAllArts(): Flow<PagingData<ArtCard>>
    fun getFavoriteArts(): Flow<PagingData<ArtCard>>
    fun addFavorite(artId: String): Boolean
    fun deleteFavorite(artId: String): Boolean
    fun isFavorite(artId: String): Flow<Boolean>
    fun getArtWork(artId: String): Flow<ArtWork>
}