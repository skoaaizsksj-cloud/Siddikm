package com.example.ffnames.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ffnames.data.model.NameStyle
import com.example.ffnames.data.model.PreloadedStyles
import com.example.ffnames.ui.viewmodel.MainViewModel
import com.example.ui.theme.FFAmberGold
import com.example.ui.theme.FFBorderColor
import com.example.ui.theme.FFDarkBackground
import com.example.ui.theme.FFDarkSurfaceCard
import com.example.ui.theme.FFDarkSurfaceVariant
import com.example.ui.theme.FFEmerald
import com.example.ui.theme.FFFireCrimson
import com.example.ui.theme.FFFireOrange
import com.example.ui.theme.FFNeonCyan
import com.example.ui.theme.FFTextMuted
import com.example.ui.theme.FFTextPrimary
import com.example.ui.theme.FFTextSecondary

@Composable
fun GeneratorScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val rawName by viewModel.rawName.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredStyles by viewModel.filteredStyles.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("screen_generator"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Hero Banner Card
        item {
            HeroBanner(
                onInvisibleSpaceClick = { viewModel.copyInvisibleSpace() },
                onRandomClick = { viewModel.setRandomPopularName() }
            )
        }

        // Input Box Section
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "আপনার নাম লিখুন (Your Nickname)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = FFTextSecondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = rawName,
                        onValueChange = { if (it.length <= 20) viewModel.rawName.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_raw_name"),
                        singleLine = true,
                        placeholder = { Text("যেমন: Siddik, Raistar, Skyler...") },
                        trailingIcon = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (rawName.isNotBlank()) {
                                    IconButton(
                                        onClick = { viewModel.rawName.value = "" },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = "Clear",
                                            tint = FFTextMuted
                                        )
                                    }
                                }
                                IconButton(
                                    onClick = { viewModel.setRandomPopularName() },
                                    modifier = Modifier
                                        .size(36.dp)
                                        .testTag("btn_random_name")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Casino,
                                        contentDescription = "Random Name",
                                        tint = FFAmberGold
                                    )
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = FFAmberGold,
                            unfocusedBorderColor = FFBorderColor,
                            focusedTextColor = FFTextPrimary,
                            unfocusedTextColor = FFTextPrimary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Search within styles
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.searchQuery.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_search_styles"),
                        singleLine = true,
                        placeholder = { Text("স্টাইল খুঁজুন (Royal, Killer, Crown, 亗...)", fontSize = 13.sp) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = FFTextMuted,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotBlank()) {
                                IconButton(
                                    onClick = { viewModel.searchQuery.value = "" },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear Search",
                                        tint = FFTextMuted
                                    )
                                }
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = FFNeonCyan,
                            unfocusedBorderColor = FFBorderColor.copy(alpha = 0.6f),
                            focusedTextColor = FFTextPrimary,
                            unfocusedTextColor = FFTextPrimary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        }

        // Category Filter Chips
        item {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PreloadedStyles.categories.forEach { category ->
                        val isSelected = selectedCategory.equals(category, ignoreCase = true)
                        CategoryChip(
                            name = category,
                            isSelected = isSelected,
                            onClick = { viewModel.selectedCategory.value = category }
                        )
                    }
                }
            }
        }

        // Section Title & Counter
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "স্টাইলিশ নাম সমূহ (${filteredStyles.size})",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = FFAmberGold
                )
                Text(
                    text = "ক্লিক করে এক ট্যাপে কপি",
                    fontSize = 11.sp,
                    color = FFTextMuted
                )
            }
        }

        // Empty state if nothing matches
        if (filteredStyles.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🔍", fontSize = 40.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "কোনো স্টাইল খুঁজে পাওয়া যায়নি",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = FFTextSecondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "অন্য কোনো কি-ওয়ার্ড লিখে অনুসন্ধান করুন",
                            fontSize = 12.sp,
                            color = FFTextMuted
                        )
                    }
                }
            }
        }

        // Name Results Cards
        items(filteredStyles, key = { it.id }) { style ->
            val formatted = style.formatName(rawName)
            val isFav = favorites.any { it.styledName == formatted }

            NameResultCard(
                style = style,
                formattedName = formatted,
                isFavorite = isFav,
                onCopy = { viewModel.copyToClipboard(formatted) },
                onPreview = { viewModel.previewStyle.value = style },
                onShare = { viewModel.shareNickname(formatted) },
                onFavoriteToggle = {
                    viewModel.toggleFavorite(formatted, rawName, style.category)
                }
            )
        }
    }
}

@Composable
private fun HeroBanner(
    onInvisibleSpaceClick: () -> Unit,
    onRandomClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFF381404),
                        Color(0xFF1E1033),
                        Color(0xFF0D1B36)
                    )
                )
            )
            .border(
                1.dp,
                Brush.horizontalGradient(listOf(FFAmberGold, FFFireCrimson, FFNeonCyan)),
                RoundedCornerShape(20.dp)
            )
            .padding(18.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(FFAmberGold.copy(alpha = 0.2f))
                            .border(1.dp, FFAmberGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🔥", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "FF Stylish Name World",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "ফ্রি ফায়ার স্টাইলিশ নিকনেম জেনারেটর",
                            fontSize = 11.sp,
                            color = FFTextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Quick Access Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onInvisibleSpaceClick,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("btn_invisible_space"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FFDarkSurfaceVariant,
                        contentColor = FFNeonCyan
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "␣ অদৃশ্য স্পেস কপি",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onRandomClick,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("btn_random_quick"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FFAmberGold,
                        contentColor = Color(0xFF160D00)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "🎲 র‍্যান্ডম নাম",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryChip(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val categoryEmoji = when (name.lowercase()) {
        "all" -> "🌐"
        "royal" -> "👑"
        "killer" -> "⚔️"
        "pro" -> "亗"
        "attitude" -> "༒"
        "bangla" -> "🇧🇩"
        "love" -> "❤️"
        "clan" -> "🛡️"
        "anime" -> "⚡"
        "symbols" -> "么"
        else -> "✨"
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) FFAmberGold else FFDarkSurfaceCard)
            .border(
                1.dp,
                if (isSelected) FFAmberGold else FFBorderColor,
                RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp)
            .testTag("chip_category_$name"),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = categoryEmoji, fontSize = 13.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = name,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color(0xFF140D00) else FFTextPrimary
            )
        }
    }
}

@Composable
fun NameResultCard(
    style: NameStyle,
    formattedName: String,
    isFavorite: Boolean,
    onCopy: () -> Unit,
    onPreview: () -> Unit,
    onShare: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("card_style_${style.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Category Badge + Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(FFDarkSurfaceVariant)
                        .border(0.5.dp, FFBorderColor, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = style.category,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = FFNeonCyan
                    )
                }

                Text(
                    text = style.title,
                    fontSize = 11.sp,
                    color = FFTextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Main Styled Name Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF0C1220))
                    .border(1.dp, Color(0xFF1F2B44), RoundedCornerShape(10.dp))
                    .clickable(onClick = onCopy)
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                Text(
                    text = formattedName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Actions Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Copy Primary Button
                Button(
                    onClick = onCopy,
                    modifier = Modifier.testTag("btn_copy_${style.id}"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FFAmberGold,
                        contentColor = Color(0xFF140D00)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy",
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "কপি",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Icon tools: Preview, Share, Favorite
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onPreview,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("btn_preview_${style.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Preview in FF Card",
                            tint = FFNeonCyan,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onShare,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = FFTextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onFavoriteToggle,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color(0xFFFF4081) else FFTextMuted,
                            modifier = Modifier.size(19.dp)
                        )
                    }
                }
            }
        }
    }
}
