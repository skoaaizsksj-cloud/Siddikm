package com.example.ffnames.data.model

data class NameStyle(
    val id: String,
    val title: String,
    val category: String,
    val prefix: String = "",
    val suffix: String = "",
    val template: String = "",
    val isCustom: Boolean = false,
    val fontStyle: FontTransformer.FontStyle = FontTransformer.FontStyle.NORMAL
) {
    fun formatName(rawName: String): String {
        val baseName = if (rawName.isBlank()) "YourName" else rawName.trim()
        val styledName = FontTransformer.transform(baseName, fontStyle)

        return if (template.isNotBlank()) {
            template.replace("{name}", styledName)
        } else {
            "${prefix}${styledName}${suffix}"
        }
    }
}
