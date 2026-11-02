package com.example.ffnames.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ffnames.data.local.FavoriteEntity
import com.example.ffnames.data.local.HistoryEntity
import com.example.ffnames.data.model.NameStyle
import com.example.ffnames.ui.viewmodel.MainViewModel
import com.example.ui.theme.FFAmberGold
import com.example.ui.theme.FFBorderColor
import com.example.ui.theme.FFDarkBackground
import com.example.ui.theme.FFDarkSurfaceCard
import com.example.ui.theme.FFDarkSurfaceVariant
import com.example.ui.theme.FFEmerald
import com.example.ui.theme.FFFireCrimson
import com.example.ui.theme.FFNeonCyan
import com.example.ui.theme.FFTextMuted
import com.example.ui.theme.FFTextPrimary
import com.example.ui.theme.FFTextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FavoritesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val favorites by viewModel.favorites.collectAsState()
    val history by viewModel.history.collectAsState()
    var selectedSubTab by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("screen_favorites")
    ) {
        // Tab selector
        TabRow(
            selectedTabIndex = selectedSubTab,
            containerColor = FFDarkSurfaceCard,
            contentColor = FFAmberGold,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedSubTab]),
                    color = FFAmberGold
                )
            }
        ) {
            Tab(
                selected = selectedSubTab == 0,
                onClick = { selectedSubTab = 0 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = if (selectedSubTab == 0) Color(0xFFFF4081) else FFTextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "প্রিয় তালিকা (${favorites.size})",
                            fontSize = 12.sp,
                            fontWeight = if (selectedSubTab == 0) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            )

            Tab(
                selected = selectedSubTab == 1,
                onClick = { selectedSubTab = 1 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null,
                            tint = if (selectedSubTab == 1) FFNeonCyan else FFTextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "কপি হিস্ট্রি (${history.size})",
                            fontSize = 12.sp,
                            fontWeight = if (selectedSubTab == 1) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            )
        }

        if (selectedSubTab == 0) {
            // Favorites List
            if (favorites.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("❤️", fontSize = 42.sp)
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "কোনো প্রিয় নাম সংরক্ষিত নেই",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = FFTextPrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "জেনারেটর থেকে ❤️ বাটনে চাপ দিয়ে যেকোনো নাম এখানে সংরক্ষণ করে রাখুন।",
                            fontSize = 12.sp,
                            color = FFTextMuted,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 10.dp, bottom = 90.dp)
                ) {
                    items(favorites, key = { it.id }) { fav ->
                        FavoriteItemCard(
                            fav = fav,
                            onCopy = { viewModel.copyToClipboard(fav.styledName) },
                            onPreview = {
                                viewModel.previewStyle.value = NameStyle(
                                    id = "fav_${fav.id}",
                                    title = fav.originalName,
                                    category = fav.category,
                                    template = fav.styledName
                                )
                            },
                            onShare = { viewModel.shareNickname(fav.styledName) },
                            onDelete = { viewModel.deleteFavorite(fav) }
                        )
                    }
                }
            }
        } else {
            // History List
            if (history.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("📋", fontSize = 42.sp)
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "কপি হিস্ট্রি খালি",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = FFTextPrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "আপনি যে নামগুলো কপি করবেন সেগুলো স্বয়ংক্রিয়ভাবে এখানে জমা থাকবে।",
                            fontSize = 12.sp,
                            color = FFTextMuted,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 10.dp, bottom = 90.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "সর্বশেষ কপি করা নাম",
                                fontSize = 12.sp,
                                color = FFTextSecondary
                            )
                            IconButton(
                                onClick = { viewModel.clearAllHistory() },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DeleteSweep,
                                    contentDescription = "Clear All History",
                                    tint = FFFireCrimson
                                )
                            }
                        }
                    }

                    items(history, key = { it.id }) { item ->
                        HistoryItemCard(
                            item = item,
                            onCopy = { viewModel.copyToClipboard(item.styledName) },
                            onDelete = { viewModel.deleteHistoryItem(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FavoriteItemCard(
    fav: FavoriteEntity,
    onCopy: () -> Unit,
    onPreview: () -> Unit,
    onShare: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(FFDarkSurfaceVariant)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = fav.category,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = FFAmberGold
                    )
                }

                Text(
                    text = "সংরক্ষিত",
                    fontSize = 10.sp,
                    color = FFTextMuted
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF0C1220))
                    .border(1.dp, Color(0xFF1E2842), RoundedCornerShape(8.dp))
                    .clickable(onClick = onCopy)
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Text(
                    text = fav.styledName,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(FFAmberGold)
                            .clickable(onClick = onCopy)
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "কপি করুন",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF140D00)
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onPreview,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Preview",
                            tint = FFNeonCyan,
                            modifier = Modifier.size(17.dp)
                        )
                    }

                    IconButton(
                        onClick = onShare,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = FFTextSecondary,
                            modifier = Modifier.size(17.dp)
                        )
                    }

                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = FFFireCrimson,
                            modifier = Modifier.size(17.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HistoryItemCard(
    item: HistoryEntity,
    onCopy: () -> Unit,
    onDelete: () -> Unit
) {
    val timeFormatted = remember(item.copiedAt) {
        val sdf = SimpleDateFormat("hh:mm a, dd MMM", Locale.getDefault())
        sdf.format(Date(item.copiedAt))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, FFBorderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.styledName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = timeFormatted,
                    fontSize = 11.sp,
                    color = FFTextMuted
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onCopy,
                    modifier = Modifier.size(34.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Re-copy",
                        tint = FFAmberGold,
                        modifier = Modifier.size(16.dp)
                    )
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(34.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = FFTextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
