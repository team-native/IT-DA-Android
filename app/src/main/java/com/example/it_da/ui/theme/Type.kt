package com.example.it_da.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private fun dotSansTextStyle(
    fontWeight: FontWeight,
    fontSize: Int
) = TextStyle(
    fontFamily = DotSans,
    fontWeight = fontWeight,
    fontSize = fontSize.sp,
    lineHeight = fontSize.sp,
    letterSpacing = 0.sp
)

val Typography = Typography(
    displayLarge = dotSansTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 23
    ),
    displayMedium = dotSansTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 21
    ),
    displaySmall = dotSansTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20
    ),
    headlineLarge = dotSansTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18
    ),
    headlineMedium = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 19
    ),
    headlineSmall = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12
    ),
    titleLarge = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 21
    ),
    titleMedium = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16
    ),
    titleSmall = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 13
    ),
    bodyLarge = dotSansTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15
    ),
    bodyMedium = dotSansTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13
    ),
    bodySmall = dotSansTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12
    ),
    labelLarge = dotSansTextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15
    ),
    labelMedium = dotSansTextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11
    ),
    labelSmall = dotSansTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10
    )
)
