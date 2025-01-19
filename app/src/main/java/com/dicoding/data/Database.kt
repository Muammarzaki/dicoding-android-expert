package com.dicoding.data

import androidx.paging.PagingSource
import androidx.room.AutoMigration
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.dicoding.domain.ArtCard
import com.dicoding.domain.RemoteKeys
import kotlinx.coroutines.flow.Flow

@Database(
    entities = [
        ArtCard::class,
        RemoteKeys::class,
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [AutoMigration(1, 2)]
)
abstract class Database : RoomDatabase() {
    abstract fun artWorkDao(): ArtWorkDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}

@Dao
interface ArtWorkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAll(artCard: List<ArtCard>)

    @Query("SELECT * FROM simple_art_data where isFavorite = 0")
    fun all(): PagingSource<Int, ArtCard>

    @Query("SELECT * FROM simple_art_data WHERE isFavorite = 1")
    fun favorites(): PagingSource<Int, ArtCard>

    @Query("UPDATE simple_art_data SET isFavorite = :isFavorite WHERE id = :artId")
    fun updateFavorite(artId: String, isFavorite: Boolean): Int

    @Query("SELECT isFavorite FROM simple_art_data WHERE id = :artId")
    fun isFavorite(artId: String): Flow<Boolean>

    @Query("DELETE FROM simple_art_data where isFavorite = 0")
    fun clear()
}

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteRemoteKeys()
}