package com.bestswlkh0310.flay.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = main30,
    secondary = main150,
    onSecondary = main150s2,
    tertiary = main100,
    background = main50,
    surface = main130,
    scrim = Color.Black.copy(0.4f)
)

private val DarkColorScheme = darkColorScheme(
    primary = primaryd,
    secondary = secondaryd,
    onSecondary = onSecondaryd,
    tertiary = tertiaryd,
    background = backgroundd,
    surface = surfaced,
    scrim = Color.Black.copy(0.4f)
)

@Composable
fun FlayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = if (darkTheme) backgroundd.toArgb() else main50.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}