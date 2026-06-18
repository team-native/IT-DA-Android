package com.example.it_da.data.store

import com.example.it_da.domain.model.Notification
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DefaultNotificationStore @Inject constructor() : NotificationStore {
    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    override val notifications = _notifications.asStateFlow()

    // Publishes the latest notification list as the shared in-memory value.
    override fun replaceNotifications(notifications: List<Notification>) {
        _notifications.value = notifications
    }

    // Updates only the selected notification while preserving list order.
    override fun markAsRead(notificationId: String) {
        _notifications.update { notifications ->
            notifications.map { notification ->
                if (notification.id == notificationId) {
                    notification.copy(isRead = true)
                } else {
                    notification
                }
            }
        }
    }

    // Updates all notifications with a single shared state publication.
    override fun markAllAsRead() {
        _notifications.update { notifications ->
            notifications.map { notification ->
                notification.copy(isRead = true)
            }
        }
    }
}
