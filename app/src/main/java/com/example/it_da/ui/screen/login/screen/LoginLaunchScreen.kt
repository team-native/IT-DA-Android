package com.example.it_da.ui.screen.login.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.screen.login.component.LoginBottomGuideText
import com.example.it_da.ui.screen.login.component.LoginIntroTextGroup
import com.example.it_da.ui.theme.ITDATheme

private val LoginLaunchIntroTopPadding = 191.dp
private val LoginLaunchBottomGuidePadding = 35.dp

// Draws the first login launch screen and delegates each text area to focused components.
@Composable
fun LoginLaunchScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            LoginIntroTextGroup(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = LoginLaunchIntroTopPadding)
            )

            LoginBottomGuideText(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = LoginLaunchBottomGuidePadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginLaunchScreenPreview() {
    ITDATheme {
        LoginLaunchScreen()
    }
}
