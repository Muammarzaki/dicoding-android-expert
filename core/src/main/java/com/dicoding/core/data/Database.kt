package com.dicoding.core.data

import androidx.paging.PagingSource
import androidx.room.AutoMigration
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.dicoding.core.domain.RemoteKeys
import kotlinx.coroutines.flow.Flow

@Database(
    entities = [
        SimpleArt::class,
        RemoteKeys::class,
        SimpleFavoriteArt::class,
    ],
    version = 3,
    exportSchema = true,
    autoMigrations = [AutoMigration(2, 3)]
)
abstract class Database : RoomDatabase() {
    abstract fun artWorkDao(): ArtWorkDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}

@Dao
interface ArtWorkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(simpleArt: List<SimpleArt>)

    @Query("SELECT * FROM simple_art_data")
    fun all(): PagingSource<Int, SimpleArt>

    @Query("SELECT * FROM favorite_art_data WHERE isFavorite = 1")
    fun favorites(): PagingSource<Int, SimpleArt>

    @Query("SELECT isFavorite FROM favorite_art_data WHERE id = :artId")
    fun isFavorite(artId: String): Flow<Boolean>

    @Query("DELETE FROM simple_art_data")
    fun clear()

    @Query("""
        INSERT INTO favorite_art_data (id, imageUrl, title, artis, year, isFavorite)
        SELECT id, imageUrl, title, artis, year, 1 FROM simple_art_data WHERE id = :artId
    """)
    fun addFavorite(artId: String)

    @Query("DELETE FROM favorite_art_data WHERE id = :artId")
    fun deleteFavorite(artId: String): Int

    @Query("SELECT count(*) FROM simple_art_data")
    fun count(): Int
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
