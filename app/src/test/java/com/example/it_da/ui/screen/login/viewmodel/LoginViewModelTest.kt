package com.example.it_da.ui.screen.login.viewmodel

import android.content.Context
import android.content.ContextWrapper
import com.example.it_da.data.auth.SocialAuthSessionStore
import com.example.it_da.data.repository.AuthSessionRepository
import com.example.it_da.data.repository.SocialAuthRepository
import com.example.it_da.domain.model.SocialAuthAccount
import com.example.it_da.domain.model.SocialAuthProvider
import com.example.it_da.testing.MainDispatcherRule
import com.example.it_da.ui.screen.login.state.LoginNavigationEffect
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val context: Context = ContextWrapper(null)

    @Test
    fun googleSignUpShowsLoadingThenNavigatesAndStoresAccountOnSuccess() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val account = SocialAuthAccount(
            provider = SocialAuthProvider.GOOGLE,
            email = "google@example.com",
            displayName = "Google User",
            idToken = "google-id-token"
        )
        val repository = DeferredSocialAuthRepository()
        val sessionStore = SocialAuthSessionStore()
        val viewModel = LoginViewModel(repository, sessionStore, FakeAuthSessionRepository())
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        runCurrent()
        viewModel.onGoogleSignUpClick(context)
        runCurrent()

        assertTrue(viewModel.uiState.value.isSocialAuthLoading)
        assertNull(viewModel.uiState.value.socialAuthErrorMessage)
        assertEquals(SocialAuthProvider.GOOGLE, repository.requestedProvider)

        repository.complete(Result.success(account))
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isSocialAuthLoading)
        assertEquals(account, sessionStore.currentAccount.value)
        assertEquals(
            LoginNavigationEffect.NavigateToSignUpAdditionalInfo,
            navigationEffect.await()
        )
    }

    @Test
    fun kakaoSignUpRequestsKakaoProviderAndNavigatesOnSuccess() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val account = SocialAuthAccount(
            provider = SocialAuthProvider.KAKAO,
            email = "kakao@example.com",
            displayName = "Kakao User",
            accessToken = "kakao-access-token"
        )
        val repository = DeferredSocialAuthRepository()
        val sessionStore = SocialAuthSessionStore()
        val viewModel = LoginViewModel(repository, sessionStore, FakeAuthSessionRepository())
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        runCurrent()
        viewModel.onKakaoSignUpClick(context)
        runCurrent()
        repository.complete(Result.success(account))
        advanceUntilIdle()

        assertEquals(SocialAuthProvider.KAKAO, repository.requestedProvider)
        assertEquals(account, sessionStore.currentAccount.value)
        assertEquals(
            LoginNavigationEffect.NavigateToSignUpAdditionalInfo,
            navigationEffect.await()
        )
    }

    @Test
    fun socialSignUpFailureStopsLoadingAndShowsErrorMessage() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val repository = DeferredSocialAuthRepository()
        val sessionStore = SocialAuthSessionStore()
        val viewModel = LoginViewModel(repository, sessionStore, FakeAuthSessionRepository())

        viewModel.onGoogleSignUpClick(context)
        runCurrent()
        repository.complete(Result.failure(IllegalStateException("Auth failed")))
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isSocialAuthLoading)
        assertEquals("Auth failed", viewModel.uiState.value.socialAuthErrorMessage)
        assertNull(sessionStore.currentAccount.value)
    }

    @Test
    fun appleSignUpShowsReadyLaterMessageWithoutCallingRepository() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val repository = DeferredSocialAuthRepository()
        val viewModel = LoginViewModel(
            repository,
            SocialAuthSessionStore(),
            FakeAuthSessionRepository()
        )

        viewModel.onAppleSignUpClick()

        assertFalse(viewModel.uiState.value.isSocialAuthLoading)
        assertEquals("Apple 회원가입은 준비 중입니다.", viewModel.uiState.value.socialAuthErrorMessage)
        assertNull(repository.requestedProvider)
    }

    @Test
    fun normalLoginNavigatesToHomeWhenRequiredInputsAreFilled() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val repository = DeferredSocialAuthRepository()
        val authSessionRepository = FakeAuthSessionRepository()
        val viewModel = LoginViewModel(
            repository,
            SocialAuthSessionStore(),
            authSessionRepository
        )
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        viewModel.onIdChange("itda-user")
        viewModel.onPasswordChange("password")
        viewModel.onLoginClick()
        advanceUntilIdle()

        assertEquals(
            LoginNavigationEffect.NavigateToHome,
            navigationEffect.await()
        )
        assertNull(repository.requestedProvider)
        assertEquals(1, authSessionRepository.saveRequestCount)
    }

    @Test
    fun normalLoginStaysOnLoginAndShowsErrorWhenSessionSaveFails() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val authSessionRepository = FakeAuthSessionRepository(
            saveResult = Result.failure(IllegalStateException("Session save failed"))
        )
        val viewModel = LoginViewModel(
            DeferredSocialAuthRepository(),
            SocialAuthSessionStore(),
            authSessionRepository
        )

        viewModel.onIdChange("itda-user")
        viewModel.onPasswordChange("password")
        viewModel.onLoginClick()
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isLoginLoading)
        assertEquals("Session save failed", viewModel.uiState.value.loginErrorMessage)
        assertEquals(1, authSessionRepository.saveRequestCount)
    }

    private class DeferredSocialAuthRepository : SocialAuthRepository {
        private val result = CompletableDeferred<Result<SocialAuthAccount>>()
        var requestedProvider: SocialAuthProvider? = null
            private set

        // Records the requested provider and waits until the test completes the auth result.
        override suspend fun authenticate(
            context: Context,
            provider: SocialAuthProvider
        ): Result<SocialAuthAccount> {
            requestedProvider = provider
            return result.await()
        }

        // Completes the suspended fake authentication request with a test-controlled result.
        fun complete(authResult: Result<SocialAuthAccount>) {
            result.complete(authResult)
        }
    }

    private class FakeAuthSessionRepository(
        private val saveResult: Result<Unit> = Result.success(Unit)
    ) : AuthSessionRepository {
        var saveRequestCount: Int = 0
            private set

        override suspend fun hasStoredToken(): Result<Boolean> {
            return Result.success(false)
        }

        // Records the temporary token request and returns the configured storage result.
        override suspend fun saveTemporaryToken(): Result<Unit> {
            saveRequestCount += 1
            return saveResult
        }

        override suspend fun clearToken(): Result<Unit> {
            return Result.success(Unit)
        }
    }
}
