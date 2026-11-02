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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ffnames.data.model.FontTransformer
import com.example.ffnames.data.model.NameStyle
import com.example.ffnames.data.model.SymbolLibrary
import com.example.ffnames.ui.viewmodel.MainViewModel
import com.example.ui.theme.FFAmberGold
import com.example.ui.theme.FFBorderColor
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
fun StudioScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val input by viewModel.studioInput.collectAsState()
    val prefix by viewModel.studioPrefix.collectAsState()
    val suffix by viewModel.studioSuffix.collectAsState()
    val fontStyle by viewModel.studioFontStyle.collectAsState()

    val currentResult = viewModel.getStudioResult()
    val charCount = currentResult.length
    val isSafe = charCount <= 14

    val popularPrefixes = listOf(
        "亗 ", "꧁ঔৣ", "♛么 ", "乂 ", "༒ ", "ᴮᴼˢˢ 乂 ", "༺", "『", "⚡ ", "𝕶𝖎𝖓𝖌 亗 ",
        "︻╦̵̵͇̿̿̿̿╤── ", "乂龍 ", "꧁༒☬", "【", "⎝⎝✧", "『αℓρнα』"
    )

    val popularSuffixes = listOf(
        " 亗", "ঔৣ꧂", " 么♛", " 乂", " ༒", " ࿐", "༻", "』", " ⚡", " 彡★",
        " 龍乂", "☬༒꧂", "】", "✧⎠⎠", " ♛", " 〆"
    )

    val quickSymbols = listOf(
        "亗", "༒", "♛", "么", "乂", "⚡", "龍", "༺", "༻", "꧁", "꧂",
        SymbolLibrary.INVISIBLE_SPACE, "•", "・", "♡", "★", "ঔৣ", "☬"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("screen_studio"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Studio Live Output Preview Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
                border = androidx.compose.foundation.BorderStroke(
                    1.5.dp,
                    Brush.horizontalGradient(listOf(FFAmberGold, FFFireCrimson))
                )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "✨ কাস্টম স্টুডিও প্রিভিউ",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = FFAmberGold
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSafe) Color(0xFF0F3822) else Color(0xFF381212))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = if (isSafe) "✓ গেম উপযোগী ($charCount chars)" else "⚠️ বড় নাম ($charCount chars)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSafe) FFEmerald else FFFireCrimson
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Styled Result Display
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF0A0F1D))
                            .border(1.dp, FFBorderColor, RoundedCornerShape(12.dp))
                            .padding(horizontal = 16.dp, vertical = 18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = currentResult,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.copyToClipboard(currentResult) },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("btn_studio_copy"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = FFAmberGold,
                                contentColor = Color(0xFF140D00)
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("কপি", fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                viewModel.previewStyle.value = NameStyle(
                                    id = "studio_preview",
                                    title = "Custom Studio",
                                    category = "Studio",
                                    template = currentResult
                                )
                            },
                            modifier = Modifier.testTag("btn_studio_preview"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = "Preview",
                                tint = FFNeonCyan,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        IconButton(
                            onClick = { viewModel.saveStudioPresetToCustomStyles() },
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(FFDarkSurfaceVariant)
                                .testTag("btn_studio_save")
                        ) {
                            Icon(
                                imageVector = Icons.Default.BookmarkAdd,
                                contentDescription = "Save Preset",
                                tint = FFAmberGold
                            )
                        }

                        IconButton(
                            onClick = { viewModel.shareNickname(currentResult) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(FFDarkSurfaceVariant)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                tint = FFTextSecondary
                            )
                        }
                    }
                }
            }
        }

        // 1. Base Name Input
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "১. মূল নাম লিখুন (Base Name)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = FFNeonCyan
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = input,
                        onValueChange = { if (it.length <= 16) viewModel.studioInput.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_studio_name"),
                        singleLine = true,
                        placeholder = { Text("যেমন: Raistar, King, BadBoy...") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = FFAmberGold,
                            unfocusedBorderColor = FFBorderColor,
                            focusedTextColor = FFTextPrimary,
                            unfocusedTextColor = FFTextPrimary
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            }
        }

        // Quick Insert Symbol Bar
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                Text(
                    text = "দ্রুত চিহ্ন ইনসার্ট করুন (Quick Symbols)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = FFTextSecondary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    quickSymbols.forEach { sym ->
                        val displaySym = if (sym == SymbolLibrary.INVISIBLE_SPACE) "␣ স্পেস" else sym
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(FFDarkSurfaceVariant)
                                .border(1.dp, FFBorderColor, RoundedCornerShape(8.dp))
                                .clickable {
                                    viewModel.studioInput.value += sym
                                }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = displaySym,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = FFAmberGold
                            )
                        }
                    }
                }
            }
        }

        // 2. Left Wing / Prefix Selector
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "২. বাম ডানা / প্রিফিক্স (Left Wing / Prefix)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = FFNeonCyan
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = prefix,
                        onValueChange = { viewModel.studioPrefix.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        placeholder = { Text("বাম পাশের চিহ্ন (ঐচ্ছিক)") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = FFAmberGold,
                            unfocusedBorderColor = FFBorderColor
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        popularPrefixes.forEach { pref ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (prefix == pref) FFAmberGold else FFDarkSurfaceVariant)
                                    .border(1.dp, FFBorderColor, RoundedCornerShape(8.dp))
                                    .clickable { viewModel.studioPrefix.value = pref }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = pref,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (prefix == pref) Color(0xFF140D00) else FFTextPrimary
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3. Font Style Selector
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "৩. ফন্ট স্টাইল নির্বাচন করুন (Font Transformer)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = FFNeonCyan
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        FontTransformer.FontStyle.values().forEach { style ->
                            val isSelected = fontStyle == style
                            val sampleTransform = FontTransformer.transform(
                                input.ifBlank { "Style" },
                                style
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) FFAmberGold.copy(alpha = 0.15f) else Color(0xFF0C1220))
                                    .border(
                                        1.dp,
                                        if (isSelected) FFAmberGold else Color(0xFF1F2B44),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable { viewModel.studioFontStyle.value = style }
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = style.displayName,
                                    fontSize = 12.sp,
                                    color = if (isSelected) FFAmberGold else FFTextSecondary
                                )
                                Text(
                                    text = sampleTransform,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        // 4. Right Wing / Suffix Selector
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "৪. ডান ডানা / সাফিক্স (Right Wing / Suffix)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = FFNeonCyan
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = suffix,
                        onValueChange = { viewModel.studioSuffix.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        placeholder = { Text("ডান পাশের চিহ্ন (ঐচ্ছিক)") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = FFAmberGold,
                            unfocusedBorderColor = FFBorderColor
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        popularSuffixes.forEach { suff ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (suffix == suff) FFAmberGold else FFDarkSurfaceVariant)
                                    .border(1.dp, FFBorderColor, RoundedCornerShape(8.dp))
                                    .clickable { viewModel.studioSuffix.value = suff }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = suff,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (suffix == suff) Color(0xFF140D00) else FFTextPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
