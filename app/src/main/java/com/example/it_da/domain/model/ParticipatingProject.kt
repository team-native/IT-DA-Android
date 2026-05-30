package com.example.it_da.domain.model

// Represents a project that the current user is already participating in.
data class ParticipatingProject(
    val id: String,
    val title: String,
    val myRole: String,
    val statusText: String,
    val teamSummary: String
)
