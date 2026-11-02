package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ffnames.ui.components.FreeFireCardPreview
import com.example.ffnames.ui.screens.FavoritesScreen
import com.example.ffnames.ui.screens.GeneratorScreen
import com.example.ffnames.ui.screens.StudioScreen
import com.example.ffnames.ui.screens.StyleManagerScreen
import com.example.ffnames.ui.screens.SymbolsScreen
import com.example.ffnames.ui.viewmodel.AppNavTab
import com.example.ffnames.ui.viewmodel.MainViewModel
import com.example.ui.theme.FFAmberGold
import com.example.ui.theme.FFBorderColor
import com.example.ui.theme.FFDarkBackground
import com.example.ui.theme.FFDarkSurface
import com.example.ui.theme.FFDarkSurfaceCard
import com.example.ui.theme.FFDarkSurfaceVariant
import com.example.ui.theme.FFEmerald
import com.example.ui.theme.FFFireCrimson
import com.example.ui.theme.FFNeonCyan
import com.example.ui.theme.FFTextMuted
import com.example.ui.theme.FFTextPrimary
import com.example.ui.theme.FFTextSecondary
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(viewModel: MainViewModel) {
    val currentTab by viewModel.currentTab.collectAsState()
    val previewStyle by viewModel.previewStyle.collectAsState()
    val rawName by viewModel.rawName.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val snackbarMessage by viewModel.snackbarMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessage()
        }
    }

    // BackHandler: When on any sub-tab, Back button returns to Generator tab
    BackHandler(enabled = currentTab != AppNavTab.GENERATOR) {
        viewModel.currentTab.value = AppNavTab.GENERATOR
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("scaffold_main"),
        containerColor = FFDarkBackground,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(FFAmberGold.copy(alpha = 0.2f))
                                .border(1.dp, FFAmberGold, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("亗", fontSize = 16.sp, color = FFAmberGold)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "FF Stylish Name World",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Black,
                                color = FFAmberGold
                            )
                            Text(
                                text = "Free Fire Nickname Generator",
                                fontSize = 11.sp,
                                color = FFTextMuted
                            )
                        }
                    }
                },
                actions = {
                    // Quick Hangul invisible space button in header
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(FFDarkSurfaceVariant)
                            .border(1.dp, FFBorderColor, RoundedCornerShape(8.dp))
                            .clickable { viewModel.copyInvisibleSpace() }
                            .padding(horizontal = 8.dp, vertical = 5.dp)
                            .testTag("topbar_btn_space"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "␣ স্পেস",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = FFNeonCyan
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Saved items count shortcut
                    IconButton(
                        onClick = { viewModel.currentTab.value = AppNavTab.FAVORITES },
                        modifier = Modifier.size(38.dp)
                    ) {
                        Box(contentAlignment = Alignment.TopEnd) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorites",
                                tint = if (currentTab == AppNavTab.FAVORITES) Color(0xFFFF4081) else FFTextSecondary,
                                modifier = Modifier.size(22.dp)
                            )
                            if (favorites.isNotEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .background(FFFireCrimson),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${favorites.size.coerceAtMost(99)}",
                                        fontSize = 8.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = FFDarkSurface,
                    titleContentColor = FFAmberGold
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .testTag("nav_bottom_bar"),
                containerColor = FFDarkSurface,
                tonalElevation = 8.dp
            ) {
                AppNavTab.values().forEach { tab ->
                    val isSelected = currentTab == tab
                    val tabIcon: ImageVector = when (tab) {
                        AppNavTab.GENERATOR -> Icons.Default.Whatshot
                        AppNavTab.STUDIO -> Icons.Default.AutoAwesome
                        AppNavTab.SYMBOLS -> Icons.Default.MenuBook
                        AppNavTab.MANAGER -> Icons.Default.Settings
                        AppNavTab.FAVORITES -> Icons.Default.Favorite
                    }

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.currentTab.value = tab },
                        icon = {
                            Icon(
                                imageVector = tabIcon,
                                contentDescription = tab.label,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            Text(
                                text = tab.banglaLabel,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF140D00),
                            selectedTextColor = FFAmberGold,
                            indicatorColor = FFAmberGold,
                            unselectedIconColor = FFTextMuted,
                            unselectedTextColor = FFTextMuted
                        ),
                        modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentTab,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "ScreenTransition"
            ) { tab ->
                when (tab) {
                    AppNavTab.GENERATOR -> GeneratorScreen(viewModel = viewModel)
                    AppNavTab.STUDIO -> StudioScreen(viewModel = viewModel)
                    AppNavTab.SYMBOLS -> SymbolsScreen(viewModel = viewModel)
                    AppNavTab.MANAGER -> StyleManagerScreen(viewModel = viewModel)
                    AppNavTab.FAVORITES -> FavoritesScreen(viewModel = viewModel)
                }
            }

            // Free Fire Simulation Card Dialog
            previewStyle?.let { style ->
                val formatted = style.formatName(rawName)
                val isFav = favorites.any { it.styledName == formatted }

                FreeFireCardPreview(
                    styledName = formatted,
                    category = style.category,
                    onDismiss = { viewModel.previewStyle.value = null },
                    onCopy = {
                        viewModel.copyToClipboard(formatted)
                        viewModel.previewStyle.value = null
                    },
                    onShare = { viewModel.shareNickname(formatted) },
                    onFavoriteToggle = {
                        viewModel.toggleFavorite(formatted, rawName, style.category)
                    },
                    isFavorite = isFav
                )
            }
        }
    }
}
