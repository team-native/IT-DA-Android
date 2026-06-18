package com.example.it_da.ui.commonComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.theme.ItdaHomeCardBorderGray

object ItdaCardDefaults {
    // Provides the standard outlined card border used by compact home-style cards.
    @Composable
    fun outlinedBorder(): BorderStroke {
        return BorderStroke(1.dp, ItdaHomeCardBorderGray)
    }
}
