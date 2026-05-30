package com.example.it_da.ui.screen.signup.viewmodel

import com.example.it_da.data.repository.AuthSessionRepository
import com.example.it_da.testing.MainDispatcherRule
import com.example.it_da.ui.screen.signup.state.SignUpAdditionalInfoNavigationEffect
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SignUpAdditionalInfoViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun completedSignUpSavesTemporarySessionThenNavigatesHome() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val repository = FakeAuthSessionRepository()
        val viewModel = SignUpAdditionalInfoViewModel(repository)
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        runCurrent()
        fillRequiredFields(viewModel)
        viewModel.onNextClick()
        advanceUntilIdle()

        assertEquals(1, repository.saveRequestCount)
        assertEquals(
            SignUpAdditionalInfoNavigationEffect.NavigateToHome,
            navigationEffect.await()
        )
    }

    @Test
    fun sessionSaveFailureKeepsSignUpScreenAndShowsError() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val repository = FakeAuthSessionRepository(
            saveResult = Result.failure(IllegalStateException("Session save failed"))
        )
        val viewModel = SignUpAdditionalInfoViewModel(repository)

        fillRequiredFields(viewModel)
        viewModel.onNextClick()
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isSessionSaving)
        assertEquals("Session save failed", viewModel.uiState.value.sessionErrorMessage)
        assertEquals(1, repository.saveRequestCount)
    }

    // Populates the form so each test exercises the session persistence branch.
    private fun fillRequiredFields(viewModel: SignUpAdditionalInfoViewModel) {
        viewModel.onNameChange("홍길동")
        viewModel.onInterestFieldChange("Back-end")
        viewModel.onTechStackChange("Kotlin")
        viewModel.onCohortChange("10기")
        viewModel.onDepartmentChange("SW과")
    }

    private class FakeAuthSessionRepository(
        private val saveResult: Result<Unit> = Result.success(Unit)
    ) : AuthSessionRepository {
        var saveRequestCount: Int = 0
            private set

        override suspend fun hasStoredToken(): Result<Boolean> {
            return Result.success(false)
        }

        override suspend fun saveTemporaryToken(): Result<Unit> {
            saveRequestCount += 1
            return saveResult
        }

        override suspend fun clearToken(): Result<Unit> {
            return Result.success(Unit)
        }
    }
}
