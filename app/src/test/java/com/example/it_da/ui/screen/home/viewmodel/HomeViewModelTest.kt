package com.example.it_da.ui.screen.home.viewmodel

import com.example.it_da.data.repository.FakeHomeRepository
import com.example.it_da.data.store.DefaultNotificationStore
import com.example.it_da.data.store.DefaultProjectStore
import com.example.it_da.data.store.DefaultUserStore
import com.example.it_da.domain.model.Notification
import com.example.it_da.domain.model.NotificationType
import com.example.it_da.domain.model.ParticipatingProject
import com.example.it_da.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun refreshesDashboardAndMapsSharedStoreValuesIntoUiState() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val viewModel = createHomeViewModel()

        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("000", uiState.userName)
        assertEquals(3, uiState.projectCount.applyingCount)
        assertEquals("AI 기반 학습 플래너 [0부0부]", uiState.recommendedProjects.first().title)
        assertEquals("사랑을 이어주는 앱 [달발]", uiState.participatingProjects.first().title)
        assertEquals(
            listOf("new-project-recommendation", "application-result"),
            uiState.notifications.map { notification -> notification.id }
        )
    }

    @Test
    fun updatesUiStateWhenProjectStorePublishesCreatedProject() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val projectStore = DefaultProjectStore()
        val viewModel = createHomeViewModel(projectStore = projectStore)
        advanceUntilIdle()

        projectStore.addCreatedProject(
            ParticipatingProject(
                id = "created-project",
                title = "새 프로젝트",
                myRole = "내 역할 : 프로젝트 생성자",
                statusText = "모집 중",
                teamSummary = "팀원 1명ㆍ마감 2026-06-30"
            )
        )
        advanceUntilIdle()

        assertEquals(2, viewModel.uiState.value.projectCount.participatingCount)
        assertEquals("새 프로젝트", viewModel.uiState.value.participatingProjects.first().title)
    }

    @Test
    fun refreshDoesNotOverwriteStoreChangesAfterInitialLoad() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val projectStore = DefaultProjectStore()
        val notificationStore = DefaultNotificationStore()
        val repository = FakeHomeRepository(
            userStore = DefaultUserStore(),
            projectStore = projectStore,
            notificationStore = notificationStore
        )
        val viewModel = HomeViewModel(repository)
        advanceUntilIdle()

        projectStore.addCreatedProject(
            ParticipatingProject(
                id = "created-project",
                title = "새 프로젝트",
                myRole = "내 역할 : 프로젝트 생성자",
                statusText = "모집 중",
                teamSummary = "팀원 1명ㆍ마감 2026-06-30"
            )
        )
        notificationStore.markAllAsRead()

        repository.refreshDashboard()
        advanceUntilIdle()

        assertEquals("새 프로젝트", viewModel.uiState.value.participatingProjects.first().title)
        assertTrue(notificationStore.notifications.value.all(Notification::isRead))
    }

    @Test
    fun limitsHomeNotificationSummaryToLatestTwoSharedNotifications() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val notificationStore = DefaultNotificationStore()
        val viewModel = createHomeViewModel(notificationStore = notificationStore)
        advanceUntilIdle()

        notificationStore.replaceNotifications(
            listOf(
                createNotification("latest"),
                createNotification("second"),
                createNotification("third")
            )
        )
        advanceUntilIdle()

        assertEquals(
            listOf("latest", "second"),
            viewModel.uiState.value.notifications.map { notification -> notification.id }
        )
    }

    private fun createHomeViewModel(
        projectStore: DefaultProjectStore = DefaultProjectStore(),
        notificationStore: DefaultNotificationStore = DefaultNotificationStore()
    ): HomeViewModel {
        return HomeViewModel(
            FakeHomeRepository(
                userStore = DefaultUserStore(),
                projectStore = projectStore,
                notificationStore = notificationStore
            )
        )
    }

    private fun createNotification(id: String): Notification {
        return Notification(
            id = id,
            type = NotificationType.MESSAGE,
            title = "알림 제목",
            message = "알림 내용",
            elapsedTime = "방금 전",
            isRead = false
        )
    }
}
