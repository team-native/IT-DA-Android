package com.example.it_da.ui.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.it_da.ui.screen.login.screen.LoginLaunchScreen
import com.example.it_da.ui.screen.login.state.LoginLaunchNavigationEffect
import com.example.it_da.ui.screen.login.viewmodel.LoginLaunchViewModel

// Connects the launch session decision to navigation while the launch screen stays display-only.
@Composable
fun LoginLaunchRoute(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: LoginLaunchViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.navigationEffect.collect { effect ->
            when (effect) {
                LoginLaunchNavigationEffect.NavigateToLogin -> onNavigateToLogin()
                LoginLaunchNavigationEffect.NavigateToHome -> onNavigateToHome()
            }
        }
    }

    LoginLaunchScreen()
}
