package com.example.movieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0XFF5451D6),
    primaryVariant = Color(0xFF7B79E2),
    onPrimary = White,
    secondary = Color(0xFFe1bee7),
    secondaryVariant = Color(0xFFfff1ff),
    error = RedErrorLight,
    background = Black2,
    onBackground = Gray300,
    surface = Black3,
    onSurface = Gray200,
)

private val LightColorPalette = lightColors(
    primary = Color(0XFF412DA8),
    primaryVariant = Color(0XFF5451D6),
    onPrimary = White,
    secondary = Color(0xFFe1bee7),
    secondaryVariant = Color(0xFFfff1ff),
    onSecondary = Black1,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = BackGroundColor,
    onBackground = onBackGround,
    surface = SurfaceColor,
    onSurface = onSurface,
)

@Composable
fun TvManiaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = NunitoTypography,
        shapes = Shapes,
        content = content
    )
}