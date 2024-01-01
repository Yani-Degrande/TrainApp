package com.example.trainapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Defines the set of typography styles for the Train Application.
 * This typography set includes various text styles used throughout the application,
 * ensuring consistency in text appearance.
 *
 * This example only overrides the `bodyLarge` text style, but other text styles can be defined similarly.
 *
 * @property bodyLarge The default text style for larger body text.
 *                     Configures font family, weight, size, line height, and letter spacing.
 * Other text styles like `titleLarge` and `labelSmall` can also be overridden similarly.
 */
val Typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
     */
    )
