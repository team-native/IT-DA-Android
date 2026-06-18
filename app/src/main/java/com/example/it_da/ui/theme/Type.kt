package com.example.it_da.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DotSansLetterSpacing = 0.sp
private const val DisplayLargeFontSize = 23
private const val DisplayMediumFontSize = 21
private const val DisplaySmallFontSize = 20
private const val HeadlineLargeFontSize = 18
private const val HeadlineMediumFontSize = 19
private const val HeadlineSmallFontSize = 12
private const val TitleLargeFontSize = 21
private const val TitleMediumFontSize = 16
private const val TitleSmallFontSize = 13
private const val BodyLargeFontSize = 15
private const val BodyMediumFontSize = 13
private const val BodySmallFontSize = 12
private const val LabelLargeFontSize = 15
private const val LabelMediumFontSize = 11
private const val LabelSmallFontSize = 10

private fun dotSansTextStyle(
    fontWeight: FontWeight,
    fontSize: Int
) = TextStyle(
    fontFamily = DotSans,
    fontWeight = fontWeight,
    fontSize = fontSize.sp,
    lineHeight = fontSize.sp,
    letterSpacing = DotSansLetterSpacing
)

val Typography = Typography(
    displayLarge = dotSansTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = DisplayLargeFontSize
    ),
    displayMedium = dotSansTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = DisplayMediumFontSize
    ),
    displaySmall = dotSansTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = DisplaySmallFontSize
    ),
    headlineLarge = dotSansTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = HeadlineLargeFontSize
    ),
    headlineMedium = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = HeadlineMediumFontSize
    ),
    headlineSmall = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = HeadlineSmallFontSize
    ),
    titleLarge = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TitleLargeFontSize
    ),
    titleMedium = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TitleMediumFontSize
    ),
    titleSmall = dotSansTextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TitleSmallFontSize
    ),
    bodyLarge = dotSansTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = BodyLargeFontSize
    ),
    bodyMedium = dotSansTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = BodyMediumFontSize
    ),
    bodySmall = dotSansTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = BodySmallFontSize
    ),
    labelLarge = dotSansTextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = LabelLargeFontSize
    ),
    labelMedium = dotSansTextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = LabelMediumFontSize
    ),
    labelSmall = dotSansTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = LabelSmallFontSize
    )
)
