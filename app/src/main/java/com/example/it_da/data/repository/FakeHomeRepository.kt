package com.example.it_da.data.repository

import com.example.it_da.data.store.NotificationStore
import com.example.it_da.data.store.ProjectStore
import com.example.it_da.data.store.UserStore
import com.example.it_da.domain.model.HomeDashboard
import com.example.it_da.domain.model.Notification
import com.example.it_da.domain.model.NotificationType
import com.example.it_da.domain.model.ParticipatingProject
import com.example.it_da.domain.model.ProjectCount
import com.example.it_da.domain.model.ProjectStoreState
import com.example.it_da.domain.model.RecommendedProject
import com.example.it_da.domain.model.UserSummary
import javax.inject.Inject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

private const val HomeNotificationSummaryLimit = 2

class FakeHomeRepository @Inject constructor(
    private val userStore: UserStore,
    private val projectStore: ProjectStore,
    private val notificationStore: NotificationStore
) : HomeRepository {
    private val initializationMutex = Mutex()
    private var isInitialized = false

    override val homeDashboard = combine(
        userStore.userSummary,
        projectStore.state,
        notificationStore.notifications
    ) { userSummary, projectState, notifications ->
        HomeDashboard(
            userName = userSummary.userName,
            greetingDescription = userSummary.greetingDescription,
            projectCount = projectState.projectCount,
            recommendedProjects = projectState.recommendedProjects,
            participatingProjects = projectState.participatingProjects,
            notifications = notifications.take(HomeNotificationSummaryLimit)
        )
    }

    // Loads sample server data once so later Store updates survive home screen re-entry.
    override suspend fun refreshDashboard(): Result<Unit> {
        return runCatching {
            initializationMutex.withLock {
                if (isInitialized) {
                    return@withLock
                }

                val dashboard = createInitialHomeDashboard()
                userStore.replaceUserSummary(
                    UserSummary(
                        userName = dashboard.userName,
                        greetingDescription = dashboard.greetingDescription
                    )
                )
                projectStore.replaceState(
                    ProjectStoreState(
                        projectCount = dashboard.projectCount,
                        recommendedProjects = dashboard.recommendedProjects,
                        participatingProjects = dashboard.participatingProjects
                    )
                )
                notificationStore.replaceNotifications(dashboard.notifications)
                isInitialized = true
            }
        }
    }
}

// Supplies fake server values until dashboard endpoints are connected.
private fun createInitialHomeDashboard(): HomeDashboard {
    return HomeDashboard(
        userName = "000",
        greetingDescription = "상상은 여기서 현실이 됩니다.\n당신의 프로젝트와 팀을 찾아보세요",
        projectCount = ProjectCount(
            applyingCount = 3,
            participatingCount = 1,
            completedCount = 1
        ),
        recommendedProjects = listOf(
            RecommendedProject(
                id = "recommended-ai-planner",
                title = "AI 기반 학습 플래너 [0부0부]",
                recruitingSummary = "백엔드 개발자 1명 모집",
                statusText = "모집 중",
                techStacks = listOf("Back-end"),
                participantSummary = "IoT과ㆍ2명, SW과 1명 참여"
            ),
            RecommendedProject(
                id = "recommended-hachiware",
                title = "하지와레 키우기 [하키]",
                recruitingSummary = "프론트엔드 개발자 2명 모집",
                statusText = "모집 중",
                techStacks = listOf("Back-end"),
                participantSummary = "IoT과ㆍ2명, SW과 1명 참여"
            ),
            RecommendedProject(
                id = "recommended-pokemon",
                title = "닮은 포켓몬 검사 [포켓몬백]",
                recruitingSummary = "iOS 개발자ㆍ1명ㆍ디자이너 1명 모집",
                statusText = "마감 임박",
                techStacks = listOf("iOS", "Design"),
                participantSummary = "IoT과ㆍ2명, SW과 1명 참여"
            )
        ),
        participatingProjects = listOf(
            ParticipatingProject(
                id = "participating-dalbal",
                title = "사랑을 이어주는 앱 [달발]",
                myRole = "내 역할 : iOS 개발",
                statusText = "진행 중",
                teamSummary = "팀원 4명ㆍ마감 2026-05-31"
            )
        ),
        notifications = createInitialNotifications()
    )
}

// Supplies one shared fake notification list for both the home summary and notification screen.
private fun createInitialNotifications(): List<Notification> {
    return listOf(
        Notification(
            id = "new-project-recommendation",
            type = NotificationType.MESSAGE,
            title = "새 프로젝트 추천",
            message = "나의 기술 스택과 일치하는 프로그램이 등록되었습니다.",
            elapsedTime = "5분 전",
            isRead = false
        ),
        Notification(
            id = "application-result",
            type = NotificationType.MESSAGE,
            title = "지원 결과 도착",
            message = "백엔드 개발자 1명이 팀에 합류했습니다.",
            elapsedTime = "1시간 전",
            isRead = false
        ),
        Notification(
            id = "team-member-joined",
            type = NotificationType.PROJECT_JOIN,
            title = "팀 멤버 합류",
            message = "백엔드 개발자 1명이 팀에 합류했습니다.",
            elapsedTime = "1시간 전",
            isRead = true
        ),
        Notification(
            id = "updated-project-recommendation",
            type = NotificationType.MESSAGE,
            title = "새 프로젝트 추천",
            message = "관심 분야 추천 프로젝트가 새로 업데이트 되었습니다.",
            elapsedTime = "1시간 전",
            isRead = true
        )
    )
}
