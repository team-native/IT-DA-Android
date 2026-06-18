package com.example.it_da.ui.screen.home.state

// Represents server-provided participating project values displayed by one project card.
data class ParticipatingProjectUiModel(
    val id: String,
    val title: String,
    val myRole: String,
    val statusText: String,
    val teamSummary: String
)
