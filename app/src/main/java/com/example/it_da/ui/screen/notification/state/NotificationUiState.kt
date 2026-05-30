package com.example.it_da.ui.screen.notification.state

import androidx.annotation.StringRes
import com.example.it_da.R

// Represents the selectable notification categories shown in the filter dropdown.
enum class NotificationFilter(
    @StringRes val labelResId: Int
) {
    ALL(R.string.notification_filter_all),
    UNREAD(R.string.notification_filter_unread),
    READ(R.string.notification_filter_read)
}

// Represents one notification item displayed on the notification screen.
data class NotificationUiModel(
    val id: String,
    val title: String,
    val description: String,
    val elapsedTime: String,
    val isRead: Boolean
)

// Represents all state needed to render the notification screen.
data class NotificationUiState(
    val notifications: List<NotificationUiModel> = emptyList(),
    val selectedFilter: NotificationFilter = NotificationFilter.ALL,
    val isFilterMenuExpanded: Boolean = false
)
