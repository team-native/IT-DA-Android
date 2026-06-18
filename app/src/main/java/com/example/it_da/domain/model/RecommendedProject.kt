package com.example.it_da.domain.model

// Represents a recommended project shared by project-related screens.
data class RecommendedProject(
    val id: String,
    val title: String,
    val recruitingSummary: String,
    val statusText: String,
    val techStacks: List<String>,
    val participantSummary: String
)
