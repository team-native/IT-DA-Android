package com.example.it_da.data.store

import com.example.it_da.domain.model.Notification
import kotlinx.coroutines.flow.StateFlow

interface NotificationStore {
    val notifications: StateFlow<List<Notification>>

    // Replaces shared notifications after a repository refresh succeeds.
    fun replaceNotifications(notifications: List<Notification>)

    // Marks one shared notification as read for every observing screen.
    fun markAsRead(notificationId: String)

    // Marks every shared notification as read for every observing screen.
    fun markAllAsRead()
}
