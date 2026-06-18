package com.example.it_da.ui.screen.login.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val LoginBottomGuideLineHeight = 16.sp

// Displays the bottom social login guide text above the system navigation area.
@Composable
fun LoginBottomGuideText(
    modifier: Modifier = Modifier
) {
    Text(
        text = "소셜 로그인으로 간편하게 가입하고 시작하세요",
        modifier = modifier.fillMaxWidth(),
        color = ItdaSecondaryTextColor,
        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = LoginBottomGuideLineHeight),
        textAlign = TextAlign.Center
    )
}
