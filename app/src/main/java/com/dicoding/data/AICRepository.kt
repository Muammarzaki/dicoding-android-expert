package com.dicoding.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dicoding.domain.AICArtWorkResponse
import com.dicoding.domain.ArtCard
import com.dicoding.domain.IAICRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class AICRepository @Inject constructor(
    private val apiClient: IAICEndpoint,
    private val database: Database
) : IAICRepository {

    override fun getArtWorks(): Flow<PagingData<ArtCard>> {
        Log.d("AICRepository", "getArtWorks called")
        return Pager(
            config = PagingConfig(pageSize = 100),
            remoteMediator = AICRemoteMediator(database, apiClient, this::getArtWorkImage),
            pagingSourceFactory = {
                database.artWorkDao().all()
            },
        ).flow
    }

    override fun getFavoriteArtWorks(): Pager<Int, ArtCard> {
        Log.d("AICRepository", "getArtWorks called")
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = AICRemoteMediator(database, apiClient, this::getArtWorkImage),
            pagingSourceFactory = {
                database.artWorkDao().favorites()
            }
        )
    }

    override suspend fun getArtWork(artId: String): AICArtWorkResponse {
        return apiClient.getArtWork(artId)
    }

    override fun getArtWorkImage(artImageId: String): String {
        return "https://www.artic.edu/iiif/2/$artImageId/$REGION/$SIZE,/$ROTATION/$QUALITY.$FORMAT"
    }

    override fun updateFavorite(artId: String, status: Boolean): Boolean =
        database.artWorkDao().updateFavorite(artId, status).let { it > 0 }

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