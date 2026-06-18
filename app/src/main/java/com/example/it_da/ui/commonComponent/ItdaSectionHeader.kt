package com.example.it_da.ui.commonComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.ItdaPrimaryTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val ItdaSectionTitleDescriptionSpacing = 12.dp
private val ItdaSectionDefaultTitleFontSize = 21.sp

// Displays a reusable section title and optional guide text with the app typography.
@Composable
fun ItdaSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    titleFontSize: TextUnit = ItdaSectionDefaultTitleFontSize,
    titleFontWeight: FontWeight = FontWeight.SemiBold
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            color = ItdaPrimaryTextColor,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = titleFontWeight,
                fontSize = titleFontSize,
                lineHeight = titleFontSize
            )
        )

        if (description != null) {
            Spacer(modifier = Modifier.height(ItdaSectionTitleDescriptionSpacing))

            Text(
                text = description,
                color = ItdaSecondaryTextColor,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
