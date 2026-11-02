package com.example.ffnames.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ffnames.data.model.SymbolCategory
import com.example.ffnames.data.model.SymbolLibrary
import com.example.ffnames.ui.viewmodel.MainViewModel
import com.example.ui.theme.FFAmberGold
import com.example.ui.theme.FFBorderColor
import com.example.ui.theme.FFDarkSurfaceCard
import com.example.ui.theme.FFDarkSurfaceVariant
import com.example.ui.theme.FFEmerald
import com.example.ui.theme.FFNeonCyan
import com.example.ui.theme.FFTextMuted
import com.example.ui.theme.FFTextPrimary
import com.example.ui.theme.FFTextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SymbolsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("screen_symbols"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Special Hangul Invisible Space Card
        item {
            InvisibleSpaceExplainerCard(
                onCopy = { viewModel.copyInvisibleSpace() }
            )
        }

        // Section header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "গেমিং চিহ্নের ভান্ডার (Gaming Symbols)",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = FFAmberGold
                )
                Text(
                    text = "ট্যাপ করে কপি করুন",
                    fontSize = 11.sp,
                    color = FFTextMuted
                )
            }
        }

        // Categorized Symbol Cards
        items(SymbolLibrary.categories, key = { it.id }) { cat ->
            SymbolCategoryCard(
                category = cat,
                onSymbolClick = { sym ->
                    viewModel.copyToClipboard(sym, "Symbol: $sym")
                }
            )
        }
    }
}

@Composable
private fun InvisibleSpaceExplainerCard(
    onCopy: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, FFNeonCyan.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = FFNeonCyan,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "অদৃশ্য স্পেস ট্রিক (Invisible Space Trick)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = FFNeonCyan
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Free Fire গেমে সাধারণ কী-বোর্ডের স্পেস গ্রহণ করে না। নিচের স্পেশাল হ্যাংগুল ফিলার (Hangul Invisible Space) কপি করে আপনার নামের মাঝে পেস্ট করুন।",
                fontSize = 12.sp,
                color = FFTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onCopy,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("btn_copy_hangul_space"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FFNeonCyan,
                    contentColor = Color(0xFF001F24)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copy",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "অদৃশ্য ফাঁকা কোড কপি করুন (ㅤ)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SymbolCategoryCard(
    category: SymbolCategory,
    onSymbolClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = category.icon, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = category.name,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = FFAmberGold
                    )
                }

                Text(
                    text = category.banglaName,
                    fontSize = 11.sp,
                    color = FFTextMuted
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                category.symbols.forEach { sym ->
                    val displayLabel = if (sym == SymbolLibrary.INVISIBLE_SPACE) "␣ স্পেস" else sym
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(FFDarkSurfaceVariant)
                            .border(1.dp, FFBorderColor, RoundedCornerShape(8.dp))
                            .clickable { onSymbolClick(sym) }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = displayLabel,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = FFTextPrimary
                        )
                    }
                }
            }
        }
    }
}
