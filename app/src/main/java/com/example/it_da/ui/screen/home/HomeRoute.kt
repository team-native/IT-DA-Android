package com.example.it_da.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.it_da.ui.screen.home.viewmodel.HomeViewModel
import com.example.it_da.ui.screen.home.viewmodel.HomeViewModelFactory

// Connects HomeViewModel state to the home screen and leaves future navigation targets as callbacks.
@Composable
fun HomeRoute(
    onProfileTabClick: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onRecommendedProjectClick = {},
        onRecommendedProjectDetailClick = {},
        onParticipatingProjectClick = {},
        onParticipatingProjectDetailClick = {},
        onNotificationClick = {},
        onViewAllNotificationsClick = {},
        onExploreProjectsClick = {},
        onHomeTabClick = {},
        onExploreTabClick = {},
        onCreateProjectClick = {},
        onNotificationTabClick = {},
        onProfileTabClick = onProfileTabClick
    )
}
