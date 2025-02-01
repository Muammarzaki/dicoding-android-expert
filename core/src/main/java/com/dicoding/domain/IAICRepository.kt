package com.dicoding.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface IAICRepository {
    fun getArtWorks(): Flow<PagingData<ArtCard>>
    fun getFavoriteArtWorks(): Flow<PagingData<ArtCard>>
    suspend fun getArtWork(artId: String): ArtWork
    fun getArtWorkImage(artImageId: String): String
    fun updateFavorite(artId: String, status: Boolean): Boolean
    fun isFavorite(artId: String): Flow<Boolean>
}