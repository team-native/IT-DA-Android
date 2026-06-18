package com.example.it_da.ui.screen.signup.state

// Represents the additional profile values that the second sign-up screen needs.
data class SignUpAdditionalInfoUiState(
    val name: String = "",
    val interestField: String = "",
    val techStack: String = "",
    val cohort: String = "",
    val department: String = "",
    val isSessionSaving: Boolean = false,
    val sessionErrorMessage: String? = null
) {
    val isNextEnabled: Boolean
        get() = name.isNotBlank() &&
            interestField.isNotBlank() &&
            techStack.isNotBlank() &&
            cohort.isNotBlank() &&
            department.isNotBlank() &&
            !isSessionSaving
}
