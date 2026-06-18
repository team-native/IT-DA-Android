package com.example.it_da.ui.screen.login.viewmodel

import com.example.it_da.data.repository.AuthSessionRepository
import com.example.it_da.testing.MainDispatcherRule
import com.example.it_da.ui.screen.login.state.LoginLaunchNavigationEffect
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginLaunchViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun storedTokenNavigatesToHomeAfterMinimumLaunchDuration() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val viewModel = LoginLaunchViewModel(
            FakeAuthSessionRepository(Result.success(true))
        )
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        runCurrent()
        advanceTimeBy(1_999L)
        runCurrent()

        assertFalse(navigationEffect.isCompleted)

        advanceTimeBy(1L)
        runCurrent()

        assertEquals(
            LoginLaunchNavigationEffect.NavigateToHome,
            navigationEffect.await()
        )
    }

    @Test
    fun missingTokenNavigatesToLoginAfterMinimumLaunchDuration() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val viewModel = LoginLaunchViewModel(
            FakeAuthSessionRepository(Result.success(false))
        )
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        runCurrent()
        advanceTimeBy(2_000L)
        runCurrent()

        assertEquals(
            LoginLaunchNavigationEffect.NavigateToLogin,
            navigationEffect.await()
        )
    }

    @Test
    fun tokenReadFailureNavigatesToLogin() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val viewModel = LoginLaunchViewModel(
            FakeAuthSessionRepository(Result.failure(IllegalStateException("Read failed")))
        )
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        runCurrent()
        advanceTimeBy(2_000L)
        runCurrent()

        assertEquals(
            LoginLaunchNavigationEffect.NavigateToLogin,
            navigationEffect.await()
        )
    }

    private class FakeAuthSessionRepository(
        private val storedTokenResult: Result<Boolean>
    ) : AuthSessionRepository {
        override suspend fun hasStoredToken(): Result<Boolean> {
            return storedTokenResult
        }

        override suspend fun saveTemporaryToken(): Result<Unit> {
            return Result.success(Unit)
        }

        override suspend fun clearToken(): Result<Unit> {
            return Result.success(Unit)
        }
    }
}
