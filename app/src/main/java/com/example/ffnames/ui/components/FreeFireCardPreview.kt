package com.example.ffnames.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.FFAmberGold
import com.example.ui.theme.FFBorderColor
import com.example.ui.theme.FFDarkBackground
import com.example.ui.theme.FFDarkSurfaceCard
import com.example.ui.theme.FFDarkSurfaceVariant
import com.example.ui.theme.FFEmerald
import com.example.ui.theme.FFFireCrimson
import com.example.ui.theme.FFFireOrange
import com.example.ui.theme.FFGrandmasterRed
import com.example.ui.theme.FFHeroicGold
import com.example.ui.theme.FFNeonCyan
import com.example.ui.theme.FFTextMuted
import com.example.ui.theme.FFTextPrimary
import com.example.ui.theme.FFTextSecondary

@Composable
fun FreeFireCardPreview(
    styledName: String,
    category: String,
    onDismiss: () -> Unit,
    onCopy: () -> Unit,
    onShare: () -> Unit,
    onFavoriteToggle: () -> Unit,
    isFavorite: Boolean
) {
    val charCount = styledName.length
    val isWithinLimit = charCount <= 14

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .testTag("dialog_ff_preview_card"),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = FFDarkSurfaceCard),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, FFBorderColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header with close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🎮",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Free Fire Card Preview",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = FFAmberGold
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("btn_close_preview")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = FFTextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Simulated Free Fire Player Banner Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF2C1608),
                                    Color(0xFF170D22),
                                    Color(0xFF0C1428)
                                )
                            )
                        )
                        .border(
                            1.5.dp,
                            Brush.horizontalGradient(listOf(FFAmberGold, FFFireCrimson, FFNeonCyan)),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column {
                        // Badge Rank row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(FFGrandmasterRed)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "亗 GRANDMASTER",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = "V-Badge",
                                    tint = FFHeroicGold,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "LVL 75",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = FFAmberGold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Avatar + Nickname
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Avatar circle with glow
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.radialGradient(
                                            listOf(FFFireOrange, Color(0xFF1A1A2E))
                                        )
                                    )
                                    .border(2.dp, FFAmberGold, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🔥",
                                    fontSize = 24.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = styledName,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "UID: 2849103982 • Region: BD",
                                    fontSize = 11.sp,
                                    color = FFTextMuted
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Guild Tag & Likes
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0x55000000))
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Guild: 亗 ELITE SQUAD 亗",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = FFNeonCyan
                            )
                            Text(
                                text = "❤️ 9,999+",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFF80AB)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Simulated Free Fire In-Game Killfeed Banner
                Text(
                    text = "IN-GAME KILLFEED SIMULATION",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = FFTextMuted,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xE6140505))
                        .border(1.dp, Color(0xFF6B1D1D), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "☠️",
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = styledName,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = FFAmberGold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "︻╦̵̵͇̿̿̿̿╤──",
                            fontSize = 11.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Enemy_Squad (HEADSHOT)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFF5252)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Length & Compatibility Info
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(FFDarkSurfaceVariant)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Category: $category",
                            fontSize = 11.sp,
                            color = FFTextSecondary
                        )
                        Text(
                            text = "Length: $charCount chars",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isWithinLimit) FFEmerald else FFFireCrimson
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isWithinLimit) Color(0xFF0F3822) else Color(0xFF3E1212))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (isWithinLimit) "✓ Free Fire Compatible" else "⚠️ May be long in-game",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isWithinLimit) FFEmerald else FFFireCrimson
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onCopy,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_preview_copy"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = FFAmberGold,
                            contentColor = Color(0xFF140D00)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "কপি করুন",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    OutlinedButton(
                        onClick = onShare,
                        modifier = Modifier
                            .weight(0.9f)
                            .testTag("btn_preview_share"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = FFNeonCyan,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "শেয়ার",
                            color = FFNeonCyan,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    IconButton(
                        onClick = onFavoriteToggle,
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isFavorite) Color(0xFF38101C) else FFDarkSurfaceVariant)
                            .testTag("btn_preview_fav")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color(0xFFFF4081) else FFTextMuted
                        )
                    }
                }
            }
        }
    }
}
