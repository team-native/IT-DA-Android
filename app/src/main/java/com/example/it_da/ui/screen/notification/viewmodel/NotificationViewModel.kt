package com.example.it_da.ui.screen.notification.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it_da.data.repository.NotificationRepository
import com.example.it_da.domain.model.Notification
import com.example.it_da.ui.screen.notification.state.NotificationFilter
import com.example.it_da.ui.screen.notification.state.NotificationUiModel
import com.example.it_da.ui.screen.notification.state.NotificationUiState
import com.example.it_da.ui.screen.notification.toNotificationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ViewModel() {
    private val selectedFilter = MutableStateFlow(NotificationFilter.ALL)
    private val isFilterMenuExpanded = MutableStateFlow(false)

    val uiState = combine(
        notificationRepository.notifications,
        selectedFilter,
        isFilterMenuExpanded
    ) { notifications, filter, isExpanded ->
        NotificationUiState(
            notifications = notifications
                .filterBy(filter)
                .map(Notification::toNotificationUiModel),
            selectedFilter = filter,
            isFilterMenuExpanded = isExpanded
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = NotificationUiState()
    )

    // Opens the filter menu so the user can choose which notification items are visible.
    fun onFilterMenuClick() {
        isFilterMenuExpanded.value = true
    }

    // Closes the filter menu without changing the currently selected category.
    fun onFilterMenuDismiss() {
        isFilterMenuExpanded.value = false
    }

    // Applies the selected category and publishes only matching shared notification items.
    fun onFilterSelected(filter: NotificationFilter) {
        selectedFilter.value = filter
        isFilterMenuExpanded.value = false
    }

    // Marks every shared notification as read through the repository.
    fun onReadAllClick() {
        viewModelScope.launch {
            notificationRepository.markAllAsRead()
        }
    }

    // Marks a selected shared notification as read through the repository.
    fun onNotificationClick(notificationId: String) {
        viewModelScope.launch {
            notificationRepository.markAsRead(notificationId)
        }
    }
}

// Filters domain notifications without leaking filtering logic into Compose UI.
private fun List<Notification>.filterBy(
    filter: NotificationFilter
): List<Notification> {
    return when (filter) {
        NotificationFilter.ALL -> this
        NotificationFilter.UNREAD -> filterNot(Notification::isRead)
        NotificationFilter.READ -> filter(Notification::isRead)
    }
}
