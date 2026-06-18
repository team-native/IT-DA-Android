package com.example.it_da.domain.model

// Represents all data needed to render the home dashboard.
data class HomeDashboard(
    val userName: String,
    val greetingDescription: String,
    val projectCount: ProjectCount,
    val recommendedProjects: List<RecommendedProject>,
    val participatingProjects: List<ParticipatingProject>,
    val notifications: List<Notification>
)
