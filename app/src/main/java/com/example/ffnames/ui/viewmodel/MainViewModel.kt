package com.example.ffnames.ui.viewmodel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ffnames.data.local.CustomStyleEntity
import com.example.ffnames.data.local.FavoriteEntity
import com.example.ffnames.data.local.HistoryEntity
import com.example.ffnames.data.model.FontTransformer
import com.example.ffnames.data.model.NameStyle
import com.example.ffnames.data.model.PreloadedStyles
import com.example.ffnames.data.model.SymbolLibrary
import com.example.ffnames.data.repository.NameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppNavTab(val label: String, val banglaLabel: String, val iconText: String) {
    GENERATOR("Generator", "জেনারেটর", "🔥"),
    STUDIO("Studio", "স্টুডিও", "✨"),
    SYMBOLS("Symbols", "চিহ্ন", "亗"),
    MANAGER("Manager", "ম্যানেজার", "⚙️"),
    FAVORITES("Saved", "সংরক্ষিত", "❤️")
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NameRepository.getInstance(application)
    private val clipboard = application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val currentTab = MutableStateFlow(AppNavTab.GENERATOR)

    // Generator Tab State
    val rawName = MutableStateFlow("Siddik")
    val selectedCategory = MutableStateFlow("All")
    val searchQuery = MutableStateFlow("")

    val allStyles: StateFlow<List<NameStyle>> = repository.allActiveStyles
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PreloadedStyles.list)

    val favorites: StateFlow<List<FavoriteEntity>> = repository.favorites
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val history: StateFlow<List<HistoryEntity>> = repository.history
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val customStyles: StateFlow<List<CustomStyleEntity>> = repository.allCustomStyles
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Filtered styles for Generator
    val filteredStyles: StateFlow<List<NameStyle>> = combine(
        allStyles,
        selectedCategory,
        searchQuery
    ) { styles, category, query ->
        styles.filter { style ->
            val matchesCategory = (category == "All" || style.category.equals(category, ignoreCase = true))
            val matchesQuery = query.isBlank() ||
                    style.title.contains(query, ignoreCase = true) ||
                    style.category.contains(query, ignoreCase = true) ||
                    style.prefix.contains(query) ||
                    style.suffix.contains(query)
            matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PreloadedStyles.list)

    // Preview Dialog State
    val previewStyle = MutableStateFlow<NameStyle?>(null)

    // Studio Tab State
    val studioInput = MutableStateFlow("Raistar")
    val studioPrefix = MutableStateFlow("亗 ")
    val studioSuffix = MutableStateFlow(" 亗")
    val studioFontStyle = MutableStateFlow(FontTransformer.FontStyle.FRAKTUR_BOLD)

    // Style Manager / Admin Form State
    val editingStyleId = MutableStateFlow<Long?>(null)
    val formTitle = MutableStateFlow("")
    val formCategory = MutableStateFlow("Royal")
    val formPrefix = MutableStateFlow("")
    val formSuffix = MutableStateFlow("")
    val formTemplate = MutableStateFlow("")
    val isFormOpen = MutableStateFlow(false)

    // Snackbar / Feedback message
    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    fun showMessage(msg: String) {
        _snackbarMessage.value = msg
    }

    fun clearMessage() {
        _snackbarMessage.value = null
    }

    fun copyToClipboard(text: String, label: String = "FF Nickname") {
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        viewModelScope.launch {
            repository.recordCopiedHistory(text)
        }
        showMessage("✓ কপি হয়েছে: $text")
    }

    fun copyInvisibleSpace() {
        copyToClipboard(SymbolLibrary.INVISIBLE_SPACE, "FF Invisible Space")
        showMessage("✓ অদৃশ্য স্পেস কপি হয়েছে! গেমের নামের মধ্যে পেস্ট করুন।")
    }

    fun shareNickname(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check my stylish Free Fire name: $text\nCreated with FF Stylish Name World")
            type = "text/plain"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Nickname").apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        getApplication<Application>().startActivity(shareIntent)
    }

    fun toggleFavorite(styledName: String, originalName: String, category: String) {
        viewModelScope.launch {
            val isFav = favorites.value.any { it.styledName == styledName }
            if (isFav) {
                repository.removeFavorite(styledName)
                showMessage("সংরক্ষিত তালিকা থেকে সরানো হয়েছে")
            } else {
                repository.addFavorite(styledName, originalName, category)
                showMessage("❤️ প্রিয় তালিকায় সংরক্ষিত হয়েছে")
            }
        }
    }

    fun deleteFavorite(entity: FavoriteEntity) {
        viewModelScope.launch {
            repository.deleteFavoriteEntity(entity)
            showMessage("মুছে ফেলা হয়েছে")
        }
    }

    fun deleteHistoryItem(entity: HistoryEntity) {
        viewModelScope.launch {
            repository.deleteHistoryItem(entity)
        }
    }

    fun clearAllHistory() {
        viewModelScope.launch {
            repository.clearHistory()
            showMessage("হিস্ট্রি মুছে ফেলা হয়েছে")
        }
    }

    fun setRandomPopularName() {
        val popularNames = listOf(
            "Raistar", "Siddik", "Skyler", "Black444", "Vincenzo",
            "Born2Kill", "Nobru", "TotalGaming", "Hunter", "Mafia",
            "Toxic", "Legend", "Sniper", "Devil", "Nawab", "King"
        )
        val randomName = popularNames.random()
        rawName.value = randomName
        showMessage("র‍্যান্ডম নাম: $randomName")
    }

    // Studio helper functions
    fun getStudioResult(): String {
        val base = studioInput.value.ifBlank { "Player" }
        val transformed = FontTransformer.transform(base, studioFontStyle.value)
        return "${studioPrefix.value}${transformed}${studioSuffix.value}"
    }

    fun saveStudioPresetToCustomStyles() {
        val result = getStudioResult()
        viewModelScope.launch {
            repository.saveCustomStyle(
                id = 0,
                title = "Studio: ${studioInput.value.take(10)}",
                category = "Studio",
                prefix = studioPrefix.value,
                suffix = studioSuffix.value,
                template = "",
                active = true
            )
            repository.addFavorite(result, studioInput.value, "Studio")
            showMessage("স্টুডিও স্টাইল সেভ হয়েছে ও তালিকায় যুক্ত হয়েছে!")
        }
    }

    // Style Manager functions
    fun openNewStyleForm() {
        editingStyleId.value = null
        formTitle.value = ""
        formCategory.value = "Royal"
        formPrefix.value = ""
        formSuffix.value = ""
        formTemplate.value = ""
        isFormOpen.value = true
    }

    fun openEditStyleForm(style: CustomStyleEntity) {
        editingStyleId.value = style.id
        formTitle.value = style.title
        formCategory.value = style.category
        formPrefix.value = style.prefix
        formSuffix.value = style.suffix
        formTemplate.value = style.template
        isFormOpen.value = true
    }

    fun closeForm() {
        isFormOpen.value = false
        editingStyleId.value = null
    }

    fun saveStyleFromForm() {
        val title = formTitle.value.trim()
        val cat = formCategory.value.trim()
        if (title.isBlank()) {
            showMessage("অনুগ্রহ করে স্টাইলের নাম লিখুন")
            return
        }
        viewModelScope.launch {
            repository.saveCustomStyle(
                id = editingStyleId.value ?: 0,
                title = title,
                category = cat.ifBlank { "Custom" },
                prefix = formPrefix.value,
                suffix = formSuffix.value,
                template = formTemplate.value,
                active = true
            )
            closeForm()
            showMessage("স্টাইল সফলভাবে সংরক্ষিত হয়েছে ✓")
        }
    }

    fun deleteCustomStyle(id: Long) {
        viewModelScope.launch {
            repository.deleteCustomStyle(id)
            showMessage("স্টাইল মুছে ফেলা হয়েছে")
        }
    }
}
