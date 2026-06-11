package com.williamlin.petrescue.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = AppColors.Primary,
    onPrimary = Color.White,

    secondary = AppColors.Secondary,
    onSecondary = Color.White,

    tertiary = AppColors.PrimarySoft,
    onTertiary = AppColors.TextPrimary,

    background = AppColors.Background,
    onBackground = AppColors.TextPrimary,

    surface = AppColors.Surface,
    onSurface = AppColors.TextPrimary
)

private val DarkColorScheme = darkColorScheme(
    primary = AppColors.PrimarySoft,
    onPrimary = AppColors.TextPrimary,

    secondary = AppColors.Secondary,
    onSecondary = Color.White,

    tertiary = AppColors.Primary,
    onTertiary = Color.White,

    background = AppColors.TextPrimary,
    onBackground = AppColors.Background,

    surface = AppColors.DarkGreen,
    onSurface = AppColors.Background
)

@Composable
fun PetRescueTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}