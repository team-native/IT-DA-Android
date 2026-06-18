package com.example.it_da.ui.commonComponent.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val ItdaUnderlinedTextButtonCornerRadius = 2.dp
private val ItdaUnderlinedTextButtonHorizontalPadding = 2.dp
private val ItdaUnderlinedTextButtonVerticalPadding = 2.dp

// Shows a compact underlined text action for secondary navigation inside cards or sections.
@Composable
fun ItdaUnderlinedTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(ItdaUnderlinedTextButtonCornerRadius))
            .clickable(onClick = onClick)
            .padding(
                horizontal = ItdaUnderlinedTextButtonHorizontalPadding,
                vertical = ItdaUnderlinedTextButtonVerticalPadding
            )
    ) {
        Text(
            text = text,
            color = ItdaSecondaryTextColor,
            style = MaterialTheme.typography.labelSmall.copy(
                textDecoration = TextDecoration.Underline
            )
        )
    }
}
