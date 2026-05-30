package com.example.it_da.ui.screen.profile

import androidx.compose.runtime.Composable

@Composable
fun NotificationSettingsRoute(
    onBackClick: () -> Unit,
    onVersionInfoClick: () -> Unit,
    onSignOutClick: () -> Unit
) {
    NotificationSettingsScreen(
        onBackClick = onBackClick,
        onVersionInfoClick = onVersionInfoClick,
        onSignOutClick = onSignOutClick
    )
}
