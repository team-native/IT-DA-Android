package com.example.it_da.data.store

import com.example.it_da.domain.model.Notification
import com.example.it_da.domain.model.NotificationType
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultNotificationStoreTest {
    @Test
    fun markAsReadUpdatesOnlySelectedNotification() {
        val store = createNotificationStore()

        store.markAsRead("first")

        assertTrue(store.notifications.value.first { it.id == "first" }.isRead)
        assertFalse(store.notifications.value.first { it.id == "second" }.isRead)
    }

    @Test
    fun markAllAsReadUpdatesEveryNotification() {
        val store = createNotificationStore()

        store.markAllAsRead()

        assertTrue(store.notifications.value.all(Notification::isRead))
    }

    private fun createNotificationStore(): DefaultNotificationStore {
        return DefaultNotificationStore().apply {
            replaceNotifications(
                listOf(
                    createNotification("first"),
                    createNotification("second")
                )
            )
        }
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
