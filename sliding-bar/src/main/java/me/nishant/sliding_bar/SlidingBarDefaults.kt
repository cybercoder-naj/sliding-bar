package me.nishant.sliding_bar

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


object SlidingBarDefaults {

    @Composable
    fun colors(
        colorPrimary: Color = MaterialTheme.colors.primary,
        colorTrack: Color = MaterialTheme.colors.onBackground,
    ) = SlidingBarColors(
        colorPrimary = colorPrimary,
        colorTrack = colorTrack
    )
}