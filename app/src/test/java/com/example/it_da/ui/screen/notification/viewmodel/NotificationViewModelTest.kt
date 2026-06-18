package com.example.it_da.ui.screen.notification.viewmodel

import com.example.it_da.data.repository.FakeNotificationRepository
import com.example.it_da.data.store.DefaultNotificationStore
import com.example.it_da.domain.model.Notification
import com.example.it_da.domain.model.NotificationType
import com.example.it_da.testing.MainDispatcherRule
import com.example.it_da.ui.screen.notification.state.NotificationFilter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun onReadAllClickMarksEverySharedNotificationAsRead() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val store = createNotificationStore()
        val viewModel = NotificationViewModel(FakeNotificationRepository(store))
        runCurrent()

        viewModel.onReadAllClick()
        advanceUntilIdle()

        assertTrue(store.notifications.value.all(Notification::isRead))
        assertTrue(viewModel.uiState.value.notifications.all { notification ->
            notification.isRead
        })
    }

    @Test
    fun onNotificationClickRemovesReadNotificationFromUnreadFilter() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val viewModel = NotificationViewModel(FakeNotificationRepository(createNotificationStore()))
        runCurrent()
        viewModel.onFilterSelected(NotificationFilter.UNREAD)
        runCurrent()
        val notificationId = viewModel.uiState.value.notifications.first().id

        viewModel.onNotificationClick(notificationId)
        advanceUntilIdle()

        assertEquals(1, viewModel.uiState.value.notifications.size)
        assertFalse(viewModel.uiState.value.notifications.any { notification ->
            notification.id == notificationId
        })
    }

    @Test
    fun onFilterSelectedClosesMenuAndPublishesMatchingNotifications() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val viewModel = NotificationViewModel(FakeNotificationRepository(createNotificationStore()))
        runCurrent()
        viewModel.onFilterMenuClick()

        viewModel.onFilterSelected(NotificationFilter.READ)
        runCurrent()

        assertFalse(viewModel.uiState.value.isFilterMenuExpanded)
        assertTrue(viewModel.uiState.value.notifications.all { notification ->
            notification.isRead
        })
    }

    private fun createNotificationStore(): DefaultNotificationStore {
        return DefaultNotificationStore().apply {
            replaceNotifications(
                listOf(
                    createNotification(id = "unread-1", isRead = false),
                    createNotification(id = "unread-2", isRead = false),
                    createNotification(id = "read", isRead = true)
                )
            )
        }
    }

    private fun createNotification(
        id: String,
        isRead: Boolean
    ): Notification {
        return Notification(
            id = id,
            type = NotificationType.MESSAGE,
            title = "알림 제목",
            message = "알림 내용",
            elapsedTime = "방금 전",
            isRead = isRead
        )
    }
}
