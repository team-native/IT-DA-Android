package com.example.it_da.data.repository

import com.example.it_da.domain.model.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    val notifications: Flow<List<Notification>>

    // Marks one notification as read through the data layer boundary.
    suspend fun markAsRead(notificationId: String): Result<Unit>

    // Marks all notifications as read through the data layer boundary.
    suspend fun markAllAsRead(): Result<Unit>
}
