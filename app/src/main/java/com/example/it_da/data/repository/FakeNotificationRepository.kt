package com.example.it_da.data.repository

import com.example.it_da.data.store.NotificationStore
import javax.inject.Inject

class FakeNotificationRepository @Inject constructor(
    private val notificationStore: NotificationStore
) : NotificationRepository {
    override val notifications = notificationStore.notifications

    // Updates shared fake notification state until a server endpoint is connected.
    override suspend fun markAsRead(notificationId: String): Result<Unit> {
        notificationStore.markAsRead(notificationId)
        return Result.success(Unit)
    }

    // Updates every shared fake notification until a server endpoint is connected.
    override suspend fun markAllAsRead(): Result<Unit> {
        notificationStore.markAllAsRead()
        return Result.success(Unit)
    }
}
