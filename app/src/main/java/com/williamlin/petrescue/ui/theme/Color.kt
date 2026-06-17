package com.williamlin.petrescue.ui.theme

import androidx.compose.ui.graphics.Color

object AppColors {
    // Exact palette from your design
    val LightBlueGray = Color(0xFFCCDBDE)
    val Sage = Color(0xFF64817D)
    val DarkGreen = Color(0xFF13281F)
    val SoftOrange = Color(0xFFFCA26E)
    val BrightOrange = Color(0xFFF5751C)

    // Theme aliases used by Theme.kt
    val Primary = BrightOrange
    val PrimarySoft = SoftOrange
    val Secondary = Sage
    val Background = LightBlueGray
    val Surface = Color.White

    // Screen sections
    val CommunityBackground = Sage

    // Cards / surfaces
    val CardBackground = Color(0xFFFFF1E8)
    val SearchBackground = Color.White
    val PostBackground = LightBlueGray
    val PlaceholderGray = Color(0xFFD9D9D9)

    // Navigation
    val NavBackground = DarkGreen
    val NavSelected = SoftOrange
    val NavIcon = CardBackground
    val NavIconSelected = CardBackground

    // Text
    val TextPrimary = DarkGreen
    val TextSecondary = Sage
    val TextOnDark = CardBackground
    val TextOnPrimary = Color.White
    val TextOnOrange = DarkGreen

    // Buttons / accents
    val FilterButtonBackground = SoftOrange
    val UrgencyBackground = SoftOrange
}