package de.mm20.launcher2.themes

import java.util.UUID


val DefaultThemeId = UUID(0L, 0L)

// Light theme color scheme
val DefaultLightColorScheme = ColorScheme<Color>(
    primary = StaticColor(0xFF51A09B.toInt()),  // Brand Color - Main
    onPrimary = StaticColor(0xFFFFFFFF.toInt()),  // Contrast text
    primaryContainer = StaticColor(0xFFDCECEB.toInt()),  // Main Light
    onPrimaryContainer = StaticColor(0xFF4A4A4A.toInt()),  // Common - Text Primary
    secondary = StaticColor(0xFF39A7FF.toInt()),  // Blue Main
    onSecondary = StaticColor(0xFFFFFFFF.toInt()),  // Contrast text
    secondaryContainer = StaticColor(0xFFEBF6FF.toInt()),  // Blue Light
    onSecondaryContainer = StaticColor(0xFF2E86CC.toInt()),  // Blue Dark
    tertiary = StaticColor(0xFF48A145.toInt()),  // Green Main
    onTertiary = StaticColor(0xFFFFFFFF.toInt()),  // Contrast text
    tertiaryContainer = StaticColor(0xFFE9F5E9.toInt()),  // Green Light
    onTertiaryContainer = StaticColor(0xFF3A8137.toInt()),  // Green Dark
    error = StaticColor(0xFFF45050.toInt()),  // Error Main
    onError = StaticColor(0xFFFFFFFF.toInt()),  // Contrast text
    errorContainer = StaticColor(0xFFFDDCDC.toInt()),  // Error Light
    onErrorContainer = StaticColor(0xFFC34040.toInt()),  // Error Dark
    surface = StaticColor(0xFFFFFFFF.toInt()),  // Background white
    onSurface = StaticColor(0xFF4A4A4A.toInt()),  // Text primary
    background = StaticColor(0xFFFFFFFF.toInt()),  // Background white
    onBackground = StaticColor(0xFF4A4A4A.toInt()),  // Text primary
    outline = StaticColor(0xFF9E9E9E.toInt()),  // Grey 500
    surfaceVariant = StaticColor(0xFFF5F5F5.toInt()),  // Grey 100
    onSurfaceVariant = StaticColor(0xFF616161.toInt()),  // Grey 700
    inverseSurface = StaticColor(0xFF4A4A4A.toInt()),  // Text primary inverse
    inverseOnSurface = StaticColor(0xFFFFFFFF.toInt()),  // Contrast text
    inversePrimary = StaticColor(0xFF51A09B.toInt()),  // Inverse Main
    surfaceTint = StaticColor(0xFF51A09B.toInt()),  // Main tint
    scrim = StaticColor(0xFF000000.toInt()),  // Scrim black

    // Additional missing parameters
    outlineVariant = StaticColor(0xFFE0E0E0.toInt()),  // Grey 300
    surfaceBright = StaticColor(0xFFFFFFFF.toInt()),  // Bright white
    surfaceContainer = StaticColor(0xFFF7F7F7.toInt()),  // Light grey
    surfaceContainerHigh = StaticColor(0xFFEFEFEF.toInt()),  // Lighter grey
    surfaceContainerHighest = StaticColor(0xFFDADADA.toInt()),  // Almost white
    surfaceContainerLow = StaticColor(0xFFF2F2F2.toInt()),  // Light grey
    surfaceContainerLowest = StaticColor(0xFFF9F9F9.toInt()),  // Very light grey
    surfaceDim = StaticColor(0xFFFAFAFA.toInt())  // Almost white
)

// Dark theme color scheme
val DefaultDarkColorScheme = ColorScheme<Color>(
    primary = StaticColor(0xFF51A09B.toInt()),  // Brand Color - Main
    onPrimary = StaticColor(0xFF4A4A4A.toInt()),  // Text primary inverse
    primaryContainer = StaticColor(0xFF41807C.toInt()),  // Main Dark
    onPrimaryContainer = StaticColor(0xFFDCECEB.toInt()),  // Main Light
    secondary = StaticColor(0xFF39A7FF.toInt()),  // Blue Main
    onSecondary = StaticColor(0xFF4A4A4A.toInt()),  // Text primary inverse
    secondaryContainer = StaticColor(0xFF2E86CC.toInt()),  // Blue Dark
    onSecondaryContainer = StaticColor(0xFFEBF6FF.toInt()),  // Blue Light
    tertiary = StaticColor(0xFF48A145.toInt()),  // Green Main
    onTertiary = StaticColor(0xFF4A4A4A.toInt()),  // Text primary inverse
    tertiaryContainer = StaticColor(0xFF3A8137.toInt()),  // Green Dark
    onTertiaryContainer = StaticColor(0xFFE9F5E9.toInt()),  // Green Light
    error = StaticColor(0xFFF45050.toInt()),  // Error Main
    onError = StaticColor(0xFF4A4A4A.toInt()),  // Text primary inverse
    errorContainer = StaticColor(0xFFC34040.toInt()),  // Error Dark
    onErrorContainer = StaticColor(0xFFFDDCDC.toInt()),  // Error Light
    surface = StaticColor(0xFF212121.toInt()),  // Grey 900
    onSurface = StaticColor(0xFFFFFFFF.toInt()),  // Contrast text
    background = StaticColor(0xFF212121.toInt()),  // Grey 900
    onBackground = StaticColor(0xFFFFFFFF.toInt()),  // Contrast text
    outline = StaticColor(0xFFBDBDBD.toInt()),  // Grey 400
    surfaceVariant = StaticColor(0xFF424242.toInt()),  // Grey 800
    onSurfaceVariant = StaticColor(0xFFBDBDBD.toInt()),  // Grey 400
    inverseSurface = StaticColor(0xFF4A4A4A.toInt()),  // Text primary inverse
    inverseOnSurface = StaticColor(0xFFFFFFFF.toInt()),  // Contrast text
    inversePrimary = StaticColor(0xFFDCECEB.toInt()),  // Inverse Light
    surfaceTint = StaticColor(0xFF51A09B.toInt()),  // Main tint
    scrim = StaticColor(0xFF000000.toInt()),  // Scrim black

    // Additional missing parameters
    outlineVariant = StaticColor(0xFFE0E0E0.toInt()),  // Grey 300
    surfaceBright = StaticColor(0xFF333333.toInt()),  // Dark grey
    surfaceContainer = StaticColor(0xFF2E2E2E.toInt()),  // Dark grey
    surfaceContainerHigh = StaticColor(0xFF3A3A3A.toInt()),  // Lighter grey
    surfaceContainerHighest = StaticColor(0xFF4A4A4A.toInt()),  // Almost black
    surfaceContainerLow = StaticColor(0xFF252525.toInt()),  // Dark grey
    surfaceContainerLowest = StaticColor(0xFF1C1C1C.toInt()),  // Very dark grey
    surfaceDim = StaticColor(0xFF181818.toInt())  // Almost black
)

// Black and White Light Color Scheme
val BlackAndWhiteThemeId = UUID(0L, 1L)

val BlackAndWhiteLightColorScheme = ColorScheme<Color?>(
    primary = StaticColor(0xFF000000.toInt()),  // Black
    onPrimary = StaticColor(0xFFFFFFFF.toInt()),  // White
    primaryContainer = StaticColor(0xFFFFFFFF.toInt()),  // White
    onPrimaryContainer = StaticColor(0xFF000000.toInt()),  // Black
    inversePrimary = StaticColor(0xFFFFFFFF.toInt()),  // White
    secondary = StaticColor(0xFF000000.toInt()),  // Black
    onSecondary = StaticColor(0xFFFFFFFF.toInt()),  // White
    secondaryContainer = StaticColor(0xFFFFFFFF.toInt()),  // White
    onSecondaryContainer = StaticColor(0xFF000000.toInt()),  // Black
    tertiary = StaticColor(0xFF000000.toInt()),  // Black
    onTertiary = StaticColor(0xFFFFFFFF.toInt()),  // White
    tertiaryContainer = StaticColor(0xFFFFFFFF.toInt()),  // White
    onTertiaryContainer = StaticColor(0xFF000000.toInt()),  // Black
    background = StaticColor(0xFFFFFFFF.toInt()),  // White
    onBackground = StaticColor(0xFF000000.toInt()),  // Black
    surface = StaticColor(0xFFFFFFFF.toInt()),  // White
    onSurface = StaticColor(0xFF000000.toInt()),  // Black
    surfaceVariant = StaticColor(0xFFFFFFFF.toInt()),  // White
    onSurfaceVariant = StaticColor(0xFF000000.toInt()),  // Black
    inverseSurface = StaticColor(0xFF000000.toInt()),  // Black
    inverseOnSurface = StaticColor(0xFFFFFFFF.toInt()),  // White
    outline = StaticColor(0xFF000000.toInt()),  // Black
    surfaceTint = StaticColor(0xFFFFFFFF.toInt()),  // White
    scrim = StaticColor(0xFF000000.toInt()),  // Black

    // Additional missing parameters
    outlineVariant = StaticColor(0xFF000000.toInt()),  // Black
    surfaceBright = StaticColor(0xFFFFFFFF.toInt()),  // White
    surfaceContainer = StaticColor(0xFFFFFFFF.toInt()),  // White
    surfaceContainerHigh = StaticColor(0xFFF7F7F7.toInt()),  // Light grey
    surfaceContainerHighest = StaticColor(0xFFEFEFEF.toInt()),  // Almost white
    surfaceContainerLow = StaticColor(0xFFF5F5F5.toInt()),  // Light grey
    surfaceContainerLowest = StaticColor(0xFFFAFAFA.toInt()),  // Very light grey
    surfaceDim = StaticColor(0xFFF0F0F0.toInt()),  // Light grey
    error = StaticColor(0xFFFF0000.toInt()),  // Red
    onError = StaticColor(0xFFFFFFFF.toInt()),  // White
    errorContainer = StaticColor(0xFFFFE0E0.toInt()),  // Light red
    onErrorContainer = StaticColor(0xFFB00000.toInt())  // Dark red
)

val BlackAndWhiteDarkColorScheme = ColorScheme<Color?>(
    primary = StaticColor(0xFFFFFFFF.toInt()),
    onPrimary = StaticColor(0xFF000000.toInt()),
    primaryContainer = StaticColor(0xFF000000.toInt()),
    onPrimaryContainer = StaticColor(0xFFFFFFFF.toInt()),
    inversePrimary = StaticColor(0xFF000000.toInt()),
    secondary = StaticColor(0xFFFFFFFF.toInt()),
    onSecondary = StaticColor(0xFF000000.toInt()),
    secondaryContainer = StaticColor(0xFF000000.toInt()),
    onSecondaryContainer = StaticColor(0xFFFFFFFF.toInt()),
    tertiary = StaticColor(0xFFFFFFFF.toInt()),
    onTertiary = StaticColor(0xFF000000.toInt()),
    tertiaryContainer = StaticColor(0xFF000000.toInt()),
    onTertiaryContainer = StaticColor(0xFFFFFFFF.toInt()),
    background = StaticColor(0xFF000000.toInt()),
    onBackground = StaticColor(0xFFFFFFFF.toInt()),
    surface = StaticColor(0xFF000000.toInt()),
    onSurface = StaticColor(0xFFFFFFFF.toInt()),
    surfaceVariant = StaticColor(0xFF000000.toInt()),
    onSurfaceVariant = StaticColor(0xFFFFFFFF.toInt()),
    inverseSurface = StaticColor(0xFFFFFFFF.toInt()),
    inverseOnSurface = StaticColor(0xFF000000.toInt()),
    error = null,
    onError = null,
    errorContainer = null,
    onErrorContainer = null,
    outline = StaticColor(0xFFFFFFFF.toInt()),
    surfaceTint = StaticColor(0xFFFFFFFF.toInt()),
    outlineVariant = StaticColor(0xFFFFFFFF.toInt()),
    scrim = StaticColor(0xFFFFFFFF.toInt()),
    surfaceDim = StaticColor(0xFF000000.toInt()),
    surfaceBright = StaticColor(0xFF000000.toInt()),
    surfaceContainer = StaticColor(0xFF000000.toInt()),
    surfaceContainerHigh = StaticColor(0xFF000000.toInt()),
    surfaceContainerHighest = StaticColor(0xFF000000.toInt()),
    surfaceContainerLow = StaticColor(0xFF000000.toInt()),
    surfaceContainerLowest = StaticColor(0xFF000000.toInt()),
)