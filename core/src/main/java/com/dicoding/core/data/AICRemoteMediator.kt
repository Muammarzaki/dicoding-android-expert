package com.dicoding.core.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dicoding.core.domain.RemoteKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class AICRemoteMediator(
    private val database: Database,
    private val apiClient: IAICEndpoint,
    private val imageResolve: (String) -> String
) : RemoteMediator<Int, SimpleArt>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SimpleArt>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val response = apiClient.getArtWorks(page, state.config.pageSize)

            val artworks = response.data.filterNot { false }
            val endOfPaginationReached =
                artworks.isEmpty() || page >= response.pagination.totalPages

            withContext(Dispatchers.IO) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        database.remoteKeysDao().deleteRemoteKeys()
                        database.artWorkDao().clear()
                    }

                    val prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1

                    val keys = artworks.map {
                        RemoteKeys(
                            id = it.id,
                            prevKey = prevKey,
                            nextKey = nextKey
                        )
                    }

                    val parsedArtworks = artworks.mapNotNull {
                        try {
                            SimpleArt.parse(it, imageResolve)
                        } catch (e: Exception) {
                            Log.e("AICRemoteMediator", "Error parsing artwork: ${it.id}", e)
                            null
                        }
                    }

                    database.remoteKeysDao().insertAll(keys)
                    database.artWorkDao().addAll(parsedArtworks)
                }
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.e("AICRemoteMediator", "Error loading artworks", e)
            return MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return withContext(Dispatchers.IO) {
            val hasData = database.artWorkDao().count() > 0
            if (hasData) InitializeAction.SKIP_INITIAL_REFRESH
            else InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX = 1
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, SimpleArt>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { data -> database.remoteKeysDao().getRemoteKeysId(data.id) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, SimpleArt>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { data -> database.remoteKeysDao().getRemoteKeysId(data.id) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, SimpleArt>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().getRemoteKeysId(id)
            }
        }
    }
}
