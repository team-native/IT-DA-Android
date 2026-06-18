package com.example.it_da.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.it_da.ui.screen.home.viewmodel.HomeViewModel

// Connects HomeViewModel state to the home screen and leaves future navigation targets as callbacks.
@Composable
fun HomeRoute(
    onCreateProjectClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onRecommendedProjectClick = {},
        onRecommendedProjectDetailClick = {},
        onParticipatingProjectClick = {},
        onParticipatingProjectDetailClick = {},
        onNotificationClick = {},
        onViewAllNotificationsClick = onNotificationClick,
        onExploreProjectsClick = {},
        onHomeTabClick = {},
        onExploreTabClick = {},
        onCreateProjectClick = onCreateProjectClick,
        onNotificationTabClick = onNotificationClick,
        onProfileTabClick = {}
    )
}
