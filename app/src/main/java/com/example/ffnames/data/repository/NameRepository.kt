package com.example.ffnames.data.repository

import android.content.Context
import androidx.room.Room
import com.example.ffnames.data.local.AppDatabase
import com.example.ffnames.data.local.CustomStyleEntity
import com.example.ffnames.data.local.FavoriteEntity
import com.example.ffnames.data.local.HistoryEntity
import com.example.ffnames.data.model.NameStyle
import com.example.ffnames.data.model.PreloadedStyles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class NameRepository(context: Context) {

    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "ff_stylish_names.db"
    ).fallbackToDestructiveMigration().build()

    private val customStyleDao = db.customStyleDao()
    private val favoriteDao = db.favoriteDao()
    private val historyDao = db.historyDao()

    /**
     * Combines built-in preloaded styles with user-created active custom styles.
     */
    val allActiveStyles: Flow<List<NameStyle>> = customStyleDao.getAllActiveStyles().map { customEntities ->
        val customStyles = customEntities.map { entity ->
            NameStyle(
                id = "custom_${entity.id}",
                title = entity.title,
                category = entity.category.ifBlank { "Custom" },
                prefix = entity.prefix,
                suffix = entity.suffix,
                template = entity.template,
                isCustom = true
            )
        }
        // User custom styles appear at the top, followed by preloaded styles
        customStyles + PreloadedStyles.list
    }

    /**
     * All custom styles (including inactive) for Style Manager screen.
     */
    val allCustomStyles: Flow<List<CustomStyleEntity>> = customStyleDao.getAllStyles()

    val favorites: Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorites()

    val history: Flow<List<HistoryEntity>> = historyDao.getRecentHistory()

    fun isFavorite(styledName: String): Flow<Boolean> = favoriteDao.isFavorite(styledName)

    suspend fun toggleFavorite(styledName: String, originalName: String, category: String) {
        // Simple check and toggle
        try {
            favoriteDao.removeFavoriteByText(styledName)
        } catch (_: Exception) {}
        // If it wasn't there, we'll insert in ViewModel based on current state
    }

    suspend fun addFavorite(styledName: String, originalName: String, category: String) {
        favoriteDao.addFavorite(
            FavoriteEntity(
                styledName = styledName,
                originalName = originalName,
                category = category
            )
        )
    }

    suspend fun removeFavorite(styledName: String) {
        favoriteDao.removeFavoriteByText(styledName)
    }

    suspend fun deleteFavoriteEntity(entity: FavoriteEntity) {
        favoriteDao.deleteFavorite(entity)
    }

    suspend fun recordCopiedHistory(styledName: String) {
        historyDao.addHistory(HistoryEntity(styledName = styledName))
    }

    suspend fun clearHistory() {
        historyDao.clearHistory()
    }

    suspend fun deleteHistoryItem(entity: HistoryEntity) {
        historyDao.deleteHistoryItem(entity)
    }

    suspend fun saveCustomStyle(
        id: Long,
        title: String,
        category: String,
        prefix: String,
        suffix: String,
        template: String,
        active: Boolean = true
    ): Long {
        val entity = CustomStyleEntity(
            id = id,
            title = title,
            category = category,
            prefix = prefix,
            suffix = suffix,
            template = template,
            active = active
        )
        return if (id == 0L) {
            customStyleDao.insertStyle(entity)
        } else {
            customStyleDao.updateStyle(entity)
            id
        }
    }

    suspend fun deleteCustomStyle(id: Long) {
        customStyleDao.deleteById(id)
    }

    companion object {
        @Volatile
        private var instance: NameRepository? = null

        fun getInstance(context: Context): NameRepository {
            return instance ?: synchronized(this) {
                instance ?: NameRepository(context).also { instance = it }
            }
        }
    }
}
