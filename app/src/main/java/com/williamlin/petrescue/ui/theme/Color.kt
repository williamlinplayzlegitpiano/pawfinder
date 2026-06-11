package com.williamlin.petrescue.ui.theme

import androidx.compose.ui.graphics.Color

object AppColors {
    //raw palette
    val LightBlueGray = Color(0xFFCCDBDE)
    val Sage = Color(0xFF64817D)
    val DarkGreen = Color(0xFF13281F)
    val SoftOrange = Color(0xFFFCA26E)
    val BrightOrange = Color(0xFFFEF2EA)
    //to-do: orange intensities

    //app theme colors
    val Background = LightBlueGray
    val Surface = Color.White
    val Primary = BrightOrange
    val PrimarySoft = SoftOrange
    val Secondary = Sage

    //text
    val TextPrimary = DarkGreen
    val TextSecondary = Sage
    val TextOnPrimary = DarkGreen

    //component
    val CardBackground = Color.White
    val PetImageBackground = SoftOrange
    val FilterButtonBackground = SoftOrange
    val ShortcutCardBackground = SoftOrange

    //urgency colors
    val UrgencyText = DarkGreen
    val UrgencyBackground = SoftOrange
}