package com.example.it_da.ui.screen.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.screen.login.component.LoginBottomGuideText
import com.example.it_da.ui.screen.login.component.LoginButton
import com.example.it_da.ui.screen.login.component.LoginInputGroup
import com.example.it_da.ui.screen.login.component.LoginIntroTextGroup
import com.example.it_da.ui.screen.login.component.LoginSignUpGuide
import com.example.it_da.ui.screen.login.component.SocialLoginButtonRow
import com.example.it_da.ui.screen.login.state.LoginUiState
import com.example.it_da.ui.theme.ITDATheme

private val LoginTopSpacing = 86.dp
private val LoginLogoIntroSpacing = 46.dp
private val LoginIntroInputSpacing = 69.dp
private val LoginInputButtonSpacing = 35.dp
private val LoginButtonSignUpSpacing = 15.dp
private val LoginSignUpSocialSpacing = 85.dp
private val LoginSocialGuideSpacing = 29.dp
private val LoginContentHorizontalPadding = 25.dp
private val LoginLogoSize = 80.dp

// Assembles the complete login screen from focused UI components.
@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onAppleLoginClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onKakaoLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .padding(horizontal = LoginContentHorizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(LoginTopSpacing))

            Image(
                painter = painterResource(id = R.drawable.itda_logo),
                contentDescription = "ITDA logo",
                modifier = Modifier.size(LoginLogoSize)
            )

            Spacer(modifier = Modifier.height(LoginLogoIntroSpacing))

            LoginIntroTextGroup()

            Spacer(modifier = Modifier.height(LoginIntroInputSpacing))

            LoginInputGroup(
                id = uiState.id,
                password = uiState.password,
                onIdChange = onIdChange,
                onPasswordChange = onPasswordChange
            )

            Spacer(modifier = Modifier.height(LoginInputButtonSpacing))

            LoginButton(
                enabled = uiState.isLoginEnabled,
                onClick = onLoginClick
            )

            Spacer(modifier = Modifier.height(LoginButtonSignUpSpacing))

            LoginSignUpGuide(
                onSignUpClick = onSignUpClick
            )

            Spacer(modifier = Modifier.height(LoginSignUpSocialSpacing))

            SocialLoginButtonRow(
                onAppleLoginClick = onAppleLoginClick,
                onGoogleLoginClick = onGoogleLoginClick,
                onKakaoLoginClick = onKakaoLoginClick
            )

            Spacer(modifier = Modifier.height(LoginSocialGuideSpacing))

            LoginBottomGuideText()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    ITDATheme {
        LoginScreen(
            uiState = LoginUiState(),
            onIdChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onSignUpClick = {},
            onAppleLoginClick = {},
            onGoogleLoginClick = {},
            onKakaoLoginClick = {}
        )
    }
}
