package com.example.it_da.ui.screen.home.state

// Represents server-provided recommended project values displayed by one project card.
data class RecommendedProjectUiModel(
    val id: String,
    val title: String,
    val recruitingSummary: String,
    val statusText: String,
    val techStacks: List<String>,
    val participantSummary: String
)
