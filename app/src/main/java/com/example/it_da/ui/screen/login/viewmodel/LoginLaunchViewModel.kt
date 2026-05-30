package com.example.it_da.ui.screen.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it_da.data.repository.AuthSessionRepository
import com.example.it_da.ui.screen.login.state.LoginLaunchNavigationEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

private const val LoginLaunchDurationMillis = 2_000L

@HiltViewModel
class LoginLaunchViewModel @Inject constructor(
    private val authSessionRepository: AuthSessionRepository
) : ViewModel() {
    private val _navigationEffect = Channel<LoginLaunchNavigationEffect>(Channel.BUFFERED)
    val navigationEffect = _navigationEffect.receiveAsFlow()

    init {
        checkStoredSession()
    }

    // Keeps the launch UI visible for at least two seconds and chooses the next authenticated destination.
    private fun checkStoredSession() {
        viewModelScope.launch {
            val storedTokenResult = async {
                authSessionRepository.hasStoredToken()
            }

            delay(LoginLaunchDurationMillis)

            val navigationEffect = if (storedTokenResult.await().getOrDefault(false)) {
                LoginLaunchNavigationEffect.NavigateToHome
            } else {
                LoginLaunchNavigationEffect.NavigateToLogin
            }

            _navigationEffect.send(navigationEffect)
        }
    }
}
