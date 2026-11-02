package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FFColorScheme = darkColorScheme(
    primary = FFAmberGold,
    onPrimary = Color(0xFF140D00),
    primaryContainer = Color(0xFF332000),
    onPrimaryContainer = FFAmberGoldBright,
    secondary = FFFireOrange,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF3D1305),
    onSecondaryContainer = Color(0xFFFFCCBC),
    tertiary = FFNeonCyan,
    onTertiary = Color(0xFF001F24),
    tertiaryContainer = Color(0xFF00363D),
    onTertiaryContainer = Color(0xFF97F0FF),
    background = FFDarkBackground,
    onBackground = FFTextPrimary,
    surface = FFDarkSurface,
    onSurface = FFTextPrimary,
    surfaceVariant = FFDarkSurfaceVariant,
    onSurfaceVariant = FFTextSecondary,
    outline = FFBorderColor,
    outlineVariant = Color(0xFF1F2B45)
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    // We enforce our signature dark gaming Free Fire aesthetic for maximum contrast and vibe
    MaterialTheme(
        colorScheme = FFColorScheme,
        typography = Typography,
        content = content
    )
}
