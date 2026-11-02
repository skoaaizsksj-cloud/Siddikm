package com.example.ffnames.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Storage
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ffnames.data.local.CustomStyleEntity
import com.example.ffnames.data.model.PreloadedStyles
import com.example.ffnames.ui.viewmodel.MainViewModel
import com.example.ui.theme.FFAmberGold
import com.example.ui.theme.FFBorderColor
import com.example.ui.theme.FFDarkSurfaceCard
import com.example.ui.theme.FFDarkSurfaceVariant
import com.example.ui.theme.FFEmerald
import com.example.ui.theme.FFFireCrimson
import com.example.ui.theme.FFNeonCyan
import com.example.ui.theme.FFTextMuted
import com.example.ui.theme.FFTextPrimary
import com.example.ui.theme.FFTextSecondary

@Composable
fun StyleManagerScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val isFormOpen by viewModel.isFormOpen.collectAsState()
    val editingId by viewModel.editingStyleId.collectAsState()
    val formTitle by viewModel.formTitle.collectAsState()
    val formCategory by viewModel.formCategory.collectAsState()
    val formPrefix by viewModel.formPrefix.collectAsState()
    val formSuffix by viewModel.formSuffix.collectAsState()
    val formTemplate by viewModel.formTemplate.collectAsState()
    val customStyles by viewModel.customStyles.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("screen_manager"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Top Info & Add button
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, FFBorderColor)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Storage,
                                contentDescription = null,
                                tint = FFAmberGold,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "স্টাইল ম্যানেজার ও এডমিন প্যানেল",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = FFAmberGold
                            )
                        }

                        if (!isFormOpen) {
                            Button(
                                onClick = { viewModel.openNewStyleForm() },
                                modifier = Modifier.testTag("btn_open_new_style_form"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = FFAmberGold,
                                    contentColor = Color(0xFF140D00)
                                ),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("নতুন স্টাইল", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "আপনার নিজস্ব স্টাইল টেমপ্লেট তৈরি ও সংরক্ষণ করুন। যেকোনো সময় নতুন প্রিফিক্স/সাফিক্স যোগ করুন যা সরাসরি জেনারেটরে যুক্ত হবে।",
                        fontSize = 12.sp,
                        color = FFTextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Form (when open)
        if (isFormOpen) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("card_style_form"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, FFAmberGold)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (editingId != null) "স্টাইল সম্পাদনা করুন" else "নতুন স্টাইল তৈরি করুন",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = FFAmberGold
                            )
                            IconButton(
                                onClick = { viewModel.closeForm() },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Form",
                                    tint = FFTextMuted
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Title
                        OutlinedTextField(
                            value = formTitle,
                            onValueChange = { viewModel.formTitle.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_form_title"),
                            label = { Text("স্টাইলের নাম (যেমন: Royal Crown)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = FFAmberGold,
                                unfocusedBorderColor = FFBorderColor
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Category
                        OutlinedTextField(
                            value = formCategory,
                            onValueChange = { viewModel.formCategory.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_form_category"),
                            label = { Text("ক্যাটাগরি (Royal, Killer, Pro, Bangla...)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = FFAmberGold,
                                unfocusedBorderColor = FFBorderColor
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = formPrefix,
                                onValueChange = { viewModel.formPrefix.value = it },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("input_form_prefix"),
                                label = { Text("Prefix (পূর্বে)") },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = FFAmberGold,
                                    unfocusedBorderColor = FFBorderColor
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )

                            OutlinedTextField(
                                value = formSuffix,
                                onValueChange = { viewModel.formSuffix.value = it },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("input_form_suffix"),
                                label = { Text("Suffix (পরে)") },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = FFAmberGold,
                                    unfocusedBorderColor = FFBorderColor
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = formTemplate,
                            onValueChange = { viewModel.formTemplate.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_form_template"),
                            label = { Text("সরাসরি টেমপ্লেট (ঐচ্ছিক, যেমন: 亗 {name} 亗)") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = FFAmberGold,
                                unfocusedBorderColor = FFBorderColor
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { viewModel.saveStyleFromForm() },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("btn_save_form_style"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = FFAmberGold,
                                    contentColor = Color(0xFF140D00)
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Save,
                                    contentDescription = "Save",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("স্টাইল সংরক্ষণ", fontWeight = FontWeight.Bold)
                            }

                            OutlinedButton(
                                onClick = { viewModel.closeForm() },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("বাতিল", color = FFTextSecondary)
                            }
                        }
                    }
                }
            }
        }

        // Custom Styles list header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "কাস্টম তৈরি স্টাইল সমূহ (${customStyles.size})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = FFAmberGold
                )
            }
        }

        if (customStyles.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "এখনো কোনো কাস্টম স্টাইল যোগ করেননি। 'নতুন স্টাইল' বোতামে চাপ দিয়ে যোগ করুন!",
                        fontSize = 12.sp,
                        color = FFTextMuted,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        } else {
            items(customStyles, key = { it.id }) { style ->
                CustomStyleItemCard(
                    style = style,
                    onEdit = { viewModel.openEditStyleForm(style) },
                    onDelete = { viewModel.deleteCustomStyle(style.id) }
                )
            }
        }

        // Built-in starter kit overview
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "বিল্ট-ইন প্রি-লোডেড স্টাইল সমূহ (${PreloadedStyles.list.size})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = FFNeonCyan
                )
                Text(
                    text = "রেডি-টু-ইউজ",
                    fontSize = 11.sp,
                    color = FFEmerald
                )
            }
        }

        items(PreloadedStyles.list.take(12), key = { "starter_${it.id}" }) { preset ->
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
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = preset.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = FFTextPrimary
                        )
                        Text(
                            text = "${preset.category} • ${preset.formatName("Siddik")}",
                            fontSize = 11.sp,
                            color = FFTextMuted
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(FFDarkSurfaceVariant)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "ডিফল্ট",
                            fontSize = 10.sp,
                            color = FFNeonCyan
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CustomStyleItemCard(
    style: CustomStyleEntity,
    onEdit: () -> Unit,
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = style.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                val sample = if (style.template.isNotBlank()) {
                    style.template.replace("{name}", "YourName")
                } else {
                    "${style.prefix}YourName${style.suffix}"
                }
                Text(
                    text = "${style.category} • $sample",
                    fontSize = 12.sp,
                    color = FFAmberGold
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onEdit,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = FFNeonCyan,
                        modifier = Modifier.size(18.dp)
                    )
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = FFFireCrimson,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
