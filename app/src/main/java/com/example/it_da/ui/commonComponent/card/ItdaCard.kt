package com.example.it_da.ui.commonComponent.card

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.commonComponent.ItdaCardDefaults
import com.example.it_da.ui.theme.ItdaWhite

private val ItdaCardCornerRadius = 8.dp

// Provides the shared card appearance while leaving all content and interactions to its caller.
@Composable
fun ItdaCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(ItdaCardCornerRadius),
        color = ItdaWhite,
        border = ItdaCardDefaults.outlinedBorder(),
        content = content
    )
}
