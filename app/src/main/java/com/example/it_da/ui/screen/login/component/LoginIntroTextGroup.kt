package com.example.it_da.ui.screen.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LoginIntroTitleDescriptionSpacing = 28.dp
private val LoginIntroDescriptionLineHeight = 20.sp

// Displays the main launch message as one grouped text element.
@Composable
fun LoginIntroTextGroup(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "학교 커뮤니티 기반 프로젝트 협력 플랫폼",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(LoginIntroTitleDescriptionSpacing))

        Text(
            text = "로그인 한 번으로 당신의 포트폴리오 첫 줄이 바뀝니다.\n퍼즐 조각처럼 딱 맞는 파트너를 만나는 곳,",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = LoginIntroDescriptionLineHeight
            ),
            textAlign = TextAlign.Center
        )
    }
}
