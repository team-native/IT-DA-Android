package com.example.it_da.ui.screen.notification.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.it_da.ui.screen.notification.screen.NotificationScreen
import com.example.it_da.ui.screen.notification.viewmodel.NotificationViewModel

// Connects notification screen events and StateFlow state to the notification UI.
@Composable
fun NotificationRoute(
    onBackClick: () -> Unit,
    onHomeTabClick: () -> Unit,
    onExploreTabClick: () -> Unit,
    onCreateProjectClick: () -> Unit,
    onNotificationTabClick: () -> Unit,
    onProfileTabClick: () -> Unit,
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    NotificationScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onReadAllClick = viewModel::onReadAllClick,
        onFilterMenuClick = viewModel::onFilterMenuClick,
        onFilterMenuDismiss = viewModel::onFilterMenuDismiss,
        onFilterSelected = viewModel::onFilterSelected,
        onNotificationClick = viewModel::onNotificationClick,
        onHomeTabClick = onHomeTabClick,
        onExploreTabClick = onExploreTabClick,
        onCreateProjectClick = onCreateProjectClick,
        onNotificationTabClick = onNotificationTabClick,
        onProfileTabClick = onProfileTabClick
    )
}
