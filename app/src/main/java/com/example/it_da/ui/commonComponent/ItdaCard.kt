package com.example.it_da.ui.commonComponent

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.theme.ItdaWhite

// Provides the shared card appearance while leaving all content and interactions to its caller.
@Composable
fun ItdaCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = ItdaWhite,
        border = ItdaCardDefaults.outlinedBorder(),
        content = content
    )
}
