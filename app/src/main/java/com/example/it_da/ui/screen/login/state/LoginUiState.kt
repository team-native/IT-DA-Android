package com.example.it_da.ui.screen.login.state

// Represents the text values that the login screen needs to display and validate.
data class LoginUiState(
    val id: String = "",
    val password: String = "",
    val isLoginLoading: Boolean = false,
    val loginErrorMessage: String? = null,
    val isSocialAuthLoading: Boolean = false,
    val socialAuthErrorMessage: String? = null
) {
    val isLoginEnabled: Boolean
        get() = id.isNotBlank() && password.isNotBlank() && !isLoginLoading
}
