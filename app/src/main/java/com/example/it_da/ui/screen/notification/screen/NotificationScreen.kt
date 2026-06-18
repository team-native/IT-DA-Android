package com.example.it_da.ui.screen.notification.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.commonComponent.ItdaLayoutDefaults
import com.example.it_da.ui.commonComponent.bar.ItdaBottomNavigationBar
import com.example.it_da.ui.screen.notification.component.NotificationBackButton
import com.example.it_da.ui.screen.notification.component.NotificationCard
import com.example.it_da.ui.screen.notification.component.NotificationFilterDropdown
import com.example.it_da.ui.screen.notification.component.NotificationHeaderSection
import com.example.it_da.ui.screen.notification.state.NotificationFilter
import com.example.it_da.ui.screen.notification.state.NotificationUiModel
import com.example.it_da.ui.screen.notification.state.NotificationUiState
import com.example.it_da.ui.theme.ITDATheme

private val NotificationCardSpacing = 12.dp
private val NotificationContentVerticalPadding = 18.dp
private val NotificationHeaderTopPadding = 26.dp
private val NotificationFilterTopPadding = 23.dp
private val NotificationCardListTopPadding = 22.dp

// Assembles the notification screen from state-driven sections and shared navigation.
@Composable
fun NotificationScreen(
    uiState: NotificationUiState,
    onBackClick: () -> Unit,
    onReadAllClick: () -> Unit,
    onFilterMenuClick: () -> Unit,
    onFilterMenuDismiss: () -> Unit,
    onFilterSelected: (NotificationFilter) -> Unit,
    onNotificationClick: (String) -> Unit,
    onHomeTabClick: () -> Unit,
    onExploreTabClick: () -> Unit,
    onCreateProjectClick: () -> Unit,
    onNotificationTabClick: () -> Unit,
    onProfileTabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            ItdaBottomNavigationBar(
                onHomeClick = onHomeTabClick,
                onExploreClick = onExploreTabClick,
                onCreateProjectClick = onCreateProjectClick,
                onNotificationClick = onNotificationTabClick,
                onProfileClick = onProfileTabClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            NotificationContent(
                uiState = uiState,
                onBackClick = onBackClick,
                onReadAllClick = onReadAllClick,
                onFilterMenuClick = onFilterMenuClick,
                onFilterMenuDismiss = onFilterMenuDismiss,
                onFilterSelected = onFilterSelected,
                onNotificationClick = onNotificationClick,
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            )
        }
    }
}

// Lays out notification controls and cards inside the screen-level scroll container.
@Composable
private fun NotificationContent(
    uiState: NotificationUiState,
    onBackClick: () -> Unit,
    onReadAllClick: () -> Unit,
    onFilterMenuClick: () -> Unit,
    onFilterMenuDismiss: () -> Unit,
    onFilterSelected: (NotificationFilter) -> Unit,
    onNotificationClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = ItdaLayoutDefaults.FormHorizontalPadding)
            .padding(vertical = NotificationContentVerticalPadding)
    ) {
        NotificationBackButton(onClick = onBackClick)

        NotificationHeaderSection(
            onReadAllClick = onReadAllClick,
            modifier = Modifier.padding(top = NotificationHeaderTopPadding)
        )

        NotificationFilterDropdown(
            selectedFilter = uiState.selectedFilter,
            isExpanded = uiState.isFilterMenuExpanded,
            onClick = onFilterMenuClick,
            onDismiss = onFilterMenuDismiss,
            onFilterSelected = onFilterSelected,
            modifier = Modifier.padding(top = NotificationFilterTopPadding)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = NotificationCardListTopPadding),
            verticalArrangement = Arrangement.spacedBy(NotificationCardSpacing)
        ) {
            uiState.notifications.forEach { notification ->
                NotificationCard(
                    notification = notification,
                    onClick = onNotificationClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationScreenPreview() {
    ITDATheme {
        NotificationScreen(
            uiState = NotificationUiState(
                notifications = listOf(
                    NotificationUiModel(
                        id = "preview-unread",
                        title = "새 프로젝트 추천",
                        description = "나의 기술 스택과 일치하는 프로그램이 등록되었습니다.",
                        elapsedTime = "5분 전",
                        isRead = false
                    ),
                    NotificationUiModel(
                        id = "preview-read",
                        title = "팀 멤버 합류",
                        description = "백엔드 개발자 1명이 팀에 합류했습니다.",
                        elapsedTime = "1시간 전",
                        isRead = true
                    )
                )
            ),
            onBackClick = {},
            onReadAllClick = {},
            onFilterMenuClick = {},
            onFilterMenuDismiss = {},
            onFilterSelected = {},
            onNotificationClick = {},
            onHomeTabClick = {},
            onExploreTabClick = {},
            onCreateProjectClick = {},
            onNotificationTabClick = {},
            onProfileTabClick = {}
        )
    }
}
