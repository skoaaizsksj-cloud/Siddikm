package com.example.ffnames.data.local

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "custom_styles")
data class CustomStyleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val category: String,
    val prefix: String = "",
    val suffix: String = "",
    val template: String = "",
    val active: Boolean = true,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "styled_name") val styledName: String,
    @ColumnInfo(name = "original_name") val originalName: String,
    val category: String,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "copied_history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "styled_name") val styledName: String,
    @ColumnInfo(name = "copied_at") val copiedAt: Long = System.currentTimeMillis()
)

@Dao
interface CustomStyleDao {
    @Query("SELECT * FROM custom_styles WHERE active = 1 ORDER BY id DESC")
    fun getAllActiveStyles(): Flow<List<CustomStyleEntity>>

    @Query("SELECT * FROM custom_styles ORDER BY id DESC")
    fun getAllStyles(): Flow<List<CustomStyleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStyle(style: CustomStyleEntity): Long

    @Update
    suspend fun updateStyle(style: CustomStyleEntity)

    @Delete
    suspend fun deleteStyle(style: CustomStyleEntity)

    @Query("DELETE FROM custom_styles WHERE id = :id")
    suspend fun deleteById(id: Long)
}

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY created_at DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE styled_name = :styledName LIMIT 1)")
    fun isFavorite(styledName: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity): Long

    @Query("DELETE FROM favorites WHERE styled_name = :styledName")
    suspend fun removeFavoriteByText(styledName: String)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)
}

@Dao
interface HistoryDao {
    @Query("SELECT * FROM copied_history ORDER BY copied_at DESC LIMIT 50")
    fun getRecentHistory(): Flow<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(history: HistoryEntity): Long

    @Query("DELETE FROM copied_history")
    suspend fun clearHistory()

    @Delete
    suspend fun deleteHistoryItem(history: HistoryEntity)
}

@Database(
    entities = [CustomStyleEntity::class, FavoriteEntity::class, HistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customStyleDao(): CustomStyleDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun historyDao(): HistoryDao
}
