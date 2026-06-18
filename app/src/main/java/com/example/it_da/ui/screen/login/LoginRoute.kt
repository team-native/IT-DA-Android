package com.example.it_da.ui.screen.login

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.it_da.ui.screen.login.screen.LoginScreen
import com.example.it_da.ui.screen.login.state.LoginNavigationEffect
import com.example.it_da.ui.screen.login.viewmodel.LoginViewModel

// Connects login ViewModel state, social auth events, and navigation callbacks to the screen.
@Composable
fun LoginRoute(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    onSocialSignUpSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val loginErrorMessage = uiState.loginErrorMessage
    val socialAuthErrorMessage = uiState.socialAuthErrorMessage

    LaunchedEffect(viewModel) {
        viewModel.navigationEffect.collect { effect ->
            when (effect) {
                LoginNavigationEffect.NavigateToHome -> onLoginSuccess()
                LoginNavigationEffect.NavigateToSignUpAdditionalInfo -> onSocialSignUpSuccess()
            }
        }
    }

    LaunchedEffect(loginErrorMessage) {
        if (loginErrorMessage != null) {
            Toast.makeText(context, loginErrorMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearLoginError()
        }
    }

    LaunchedEffect(socialAuthErrorMessage) {
        if (socialAuthErrorMessage != null) {
            Toast.makeText(context, socialAuthErrorMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearSocialAuthError()
        }
    }

    LoginScreen(
        uiState = uiState,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginClick,
        onSignUpClick = onSignUpClick,
        onAppleLoginClick = viewModel::onAppleSignUpClick,
        onGoogleLoginClick = {
            viewModel.onGoogleSignUpClick(context)
        },
        onKakaoLoginClick = {
            viewModel.onKakaoSignUpClick(context)
        }
    )
}
