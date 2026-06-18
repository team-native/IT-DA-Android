package com.example.it_da.ui.screen.login.state

// Represents the one-time destination selected after the launch session check.
sealed interface LoginLaunchNavigationEffect {
    data object NavigateToLogin : LoginLaunchNavigationEffect
    data object NavigateToHome : LoginLaunchNavigationEffect
}
