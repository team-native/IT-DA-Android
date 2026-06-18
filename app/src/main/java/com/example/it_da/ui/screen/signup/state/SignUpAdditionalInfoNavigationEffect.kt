package com.example.it_da.ui.screen.signup.state

// Represents one-time navigation emitted after the sign-up session has been saved.
sealed interface SignUpAdditionalInfoNavigationEffect {
    data object NavigateToHome : SignUpAdditionalInfoNavigationEffect
}
