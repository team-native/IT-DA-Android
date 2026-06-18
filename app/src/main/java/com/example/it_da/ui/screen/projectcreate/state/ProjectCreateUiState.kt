package com.example.it_da.ui.screen.projectcreate.state

// Represents every value the project creation screen needs to render and submit.
data class ProjectCreateUiState(
    val projectName: String = "",
    val category: String = "",
    val period: String = "",
    val method: String = "",
    val introduction: String = "",
    val goal: String = "",
    val memberCount: String = "",
    val role: String = "",
    val techStack: String = "",
    val deadline: String = "",
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null,
    val isSubmitSuccess: Boolean = false
) {
    val isSubmitEnabled: Boolean
        get() = projectName.isNotBlank() &&
            category.isNotBlank() &&
            period.isNotBlank() &&
            method.isNotBlank() &&
            introduction.isNotBlank() &&
            goal.isNotBlank() &&
            memberCount.isNotBlank() &&
            role.isNotBlank() &&
            techStack.isNotBlank() &&
            deadline.isNotBlank() &&
            !isSubmitting
}
