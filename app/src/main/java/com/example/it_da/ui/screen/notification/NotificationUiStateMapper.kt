package com.example.it_da.ui.screen.notification

import com.example.it_da.domain.model.Notification
import com.example.it_da.ui.screen.notification.state.NotificationUiModel

// Converts shared domain notification values into notification screen UI values.
fun Notification.toNotificationUiModel(): NotificationUiModel {
    return NotificationUiModel(
        id = id,
        title = title,
        description = message,
        elapsedTime = elapsedTime,
        isRead = isRead
    )
}
