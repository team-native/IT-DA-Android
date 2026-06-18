package com.example.it_da.ui.commonComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.theme.ItdaGuideGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val ItdaOutlinedBadgeCornerRadius = 30.dp
private val ItdaOutlinedBadgeBorderWidth = 1.2.dp
private val ItdaOutlinedBadgeHorizontalPadding = 12.dp
private val ItdaOutlinedBadgeVerticalPadding = 6.dp

// Displays a rounded outlined badge for compact labels such as status or tech stack text.
@Composable
fun ItdaOutlinedBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(ItdaOutlinedBadgeCornerRadius),
        color = androidx.compose.ui.graphics.Color.Transparent,
        border = BorderStroke(ItdaOutlinedBadgeBorderWidth, ItdaGuideGray)
    ) {
        Text(
            text = text,
            color = ItdaSecondaryTextColor,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(
                horizontal = ItdaOutlinedBadgeHorizontalPadding,
                vertical = ItdaOutlinedBadgeVerticalPadding
            )
        )
    }
}
