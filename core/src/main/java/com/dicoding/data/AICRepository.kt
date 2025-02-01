package com.dicoding.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dicoding.domain.ArtCard
import com.dicoding.domain.ArtWork
import com.dicoding.domain.IAICRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class AICRepository @Inject constructor(
    private val apiClient: IAICEndpoint,
    private val database: Database
) : IAICRepository {

    override fun getArtWorks(): Flow<PagingData<ArtCard>> {
        Log.d("AICRepository", "getArtWorks called")
        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = AICRemoteMediator(database, apiClient, this::getArtWorkImage),
            pagingSourceFactory = {
                database.artWorkDao().all()
            },
        ).flow.map { paging ->
            paging.map {
                ArtCard(
                    id = it.id,
                    imageUrl = it.imageUrl,
                    title = it.title,
                    artis = it.artis,
                    year = it.year,
                )
            }
        }
    }

    override fun getFavoriteArtWorks(): Flow<PagingData<ArtCard>> {
        Log.d("AICRepository", "getArtWorks called")
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = AICRemoteMediator(database, apiClient, this::getArtWorkImage),
            pagingSourceFactory = {
                database.artWorkDao().favorites()
            }
        ).flow.map { list ->
            list.map {
                ArtCard(
                    id = it.id,
                    imageUrl = it.imageUrl,
                    title = it.title,
                    artis = it.artis,
                    year = it.year,
                )
            }
        }
    }

    override suspend fun getArtWork(artId: String): ArtWork {
        return apiClient.getArtWork(artId)
            .data.let {
                ArtWork.parse(it).copy(thumbnail = getArtWorkImage(it.imageId))
            }
    }

    override fun getArtWorkImage(artImageId: String): String {
        return "https://www.artic.edu/iiif/2/$artImageId/$REGION/$SIZE,/$ROTATION/$QUALITY.$FORMAT"
    }

    override fun updateFavorite(artId: String, status: Boolean): Boolean {
        return if (status) {
            database.artWorkDao()
                .addFavorite(artId)
            true
        } else {
            database.artWorkDao().deleteFavorite(artId).let { it > 0 }
        }
    }

    override fun isFavorite(artId: String): Flow<Boolean> =
        database.artWorkDao().isFavorite(artId)


    companion object {
        const val REGION = "full"
        const val SIZE = 400
        const val ROTATION = 0
        const val QUALITY = "default"
        const val FORMAT = "jpg"
    }
}