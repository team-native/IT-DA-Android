package com.example.it_da.ui.screen.home

import com.example.it_da.R
import com.example.it_da.domain.model.HomeDashboard
import com.example.it_da.domain.model.Notification
import com.example.it_da.domain.model.NotificationType
import com.example.it_da.domain.model.ParticipatingProject
import com.example.it_da.domain.model.ProjectCount
import com.example.it_da.domain.model.RecommendedProject
import com.example.it_da.ui.screen.home.state.HomeNotificationUiModel
import com.example.it_da.ui.screen.home.state.HomeProjectCountUiModel
import com.example.it_da.ui.screen.home.state.HomeUiState
import com.example.it_da.ui.screen.home.state.ParticipatingProjectUiModel
import com.example.it_da.ui.screen.home.state.RecommendedProjectUiModel

// Converts domain home dashboard data into UI state for the Home screen.
fun HomeDashboard.toHomeUiState(): HomeUiState {
    return HomeUiState(
        userName = userName,
        profileImageResId = R.drawable.home_profile_placeholder,
        greetingDescription = greetingDescription,
        projectCount = projectCount.toHomeProjectCountUiModel(),
        recommendedProjects = recommendedProjects.map { project ->
            project.toRecommendedProjectUiModel()
        },
        participatingProjects = participatingProjects.map { project ->
            project.toParticipatingProjectUiModel()
        },
        notifications = notifications.map { notification ->
            notification.toHomeNotificationUiModel()
        }
    )
}

// Converts domain project count data into the count model used by the Home header.
private fun ProjectCount.toHomeProjectCountUiModel(): HomeProjectCountUiModel {
    return HomeProjectCountUiModel(
        applyingCount = applyingCount,
        participatingCount = participatingCount,
        completedCount = completedCount
    )
}

// Converts a domain recommended project into the card model used by the Home UI.
private fun RecommendedProject.toRecommendedProjectUiModel(): RecommendedProjectUiModel {
    return RecommendedProjectUiModel(
        id = id,
        title = title,
        recruitingSummary = recruitingSummary,
        statusText = statusText,
        techStacks = techStacks,
        participantSummary = participantSummary
    )
}

// Converts a domain participating project into the card model used by the Home UI.
private fun ParticipatingProject.toParticipatingProjectUiModel(): ParticipatingProjectUiModel {
    return ParticipatingProjectUiModel(
        id = id,
        title = title,
        myRole = myRole,
        statusText = statusText,
        teamSummary = teamSummary
    )
}

// Converts a domain notification into the image-backed model used by the Home UI.
private fun Notification.toHomeNotificationUiModel(): HomeNotificationUiModel {
    return HomeNotificationUiModel(
        id = id,
        imageResId = type.toNotificationImageResId(),
        imageDescription = type.toNotificationImageDescription(),
        message = message,
        elapsedTime = elapsedTime
    )
}

// Maps notification type values to drawable resources owned by the UI layer.
private fun NotificationType.toNotificationImageResId(): Int {
    return when (this) {
        NotificationType.MESSAGE -> R.drawable.home_notification_mailbox
        NotificationType.PROJECT_JOIN -> R.drawable.home_notification_laptop
    }
}

// Maps notification type values to accessibility descriptions for notification images.
private fun NotificationType.toNotificationImageDescription(): String {
    return when (this) {
        NotificationType.MESSAGE -> "새 메시지 알림"
        NotificationType.PROJECT_JOIN -> "프로젝트 참여 알림"
    }
}
