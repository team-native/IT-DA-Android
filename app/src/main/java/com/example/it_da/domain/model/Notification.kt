package com.example.it_da.domain.model

// Represents one notification shared by the home summary and notification screen.
data class Notification(
    val id: String,
    val type: NotificationType,
    val title: String,
    val message: String,
    val elapsedTime: String,
    val isRead: Boolean
)
