package com.example.it_da.ui.screen.profile

import androidx.compose.runtime.Composable

// Connects profile screen callbacks with navigation from the graph.
@Composable
fun ProfileRoute(
    onHomeTabClick: () -> Unit,
    onNotificationSettingsClick: () -> Unit,
    onProjectStatusClick: () -> Unit,
    onSelfIntroductionClick: () -> Unit,
    onPersonalInfoClick: () -> Unit
) {
    MyProfileScreen(
        onHomeTabClick = onHomeTabClick,
        onExploreTabClick = {},
        onCreateProjectClick = {},
        onNotificationTabClick = {},
        onProfileTabClick = {},
        onNotificationSettingsClick = onNotificationSettingsClick,
        onProjectStatusClick = onProjectStatusClick,
        onSelfIntroductionClick = onSelfIntroductionClick,
        onPersonalInfoClick = onPersonalInfoClick
    )
}
