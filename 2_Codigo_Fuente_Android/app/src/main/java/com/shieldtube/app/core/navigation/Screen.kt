package com.shieldtube.app.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Destinos de navegación dentro de ShieldTube.
 */
sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : Screen(
        route = "home",
        title = "Inicio",
        icon = Icons.Default.Home
    )

    data object YouTube : Screen(
        route = "youtube",
        title = "YouTube",
        icon = Icons.Default.PlayCircle
    )

    data object Settings : Screen(
        route = "settings",
        title = "Ajustes",
        icon = Icons.Default.Settings
    )

    data object Guide : Screen(
        route = "guide",
        title = "Guía",
        icon = Icons.Default.HelpOutline
    )

    companion object {
        val bottomNavItems = listOf(Home, YouTube, Settings, Guide)
    }
}
