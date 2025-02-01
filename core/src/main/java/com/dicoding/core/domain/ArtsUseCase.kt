package com.dicoding.core.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtsUseCase @Inject constructor(
    private val repository: IAICRepository
) : IArtsUseCase {
    override fun getAllArts(): Flow<PagingData<ArtCard>> {
        return repository.getArtWorks()
    }

    override fun getFavoriteArts(): Flow<PagingData<ArtCard>> =
        repository.getFavoriteArtWorks()


    override fun addFavorite(artId: String): Boolean =
        repository.updateFavorite(artId, true)

    override fun deleteFavorite(artId: String): Boolean =
        repository.updateFavorite(artId, false)

    override fun isFavorite(artId: String): Flow<Boolean> = repository.isFavorite(artId)

    override fun getArtWork(artId: String): Flow<ArtWork> = flow {
        emit(repository.getArtWork(artId))
    }
}