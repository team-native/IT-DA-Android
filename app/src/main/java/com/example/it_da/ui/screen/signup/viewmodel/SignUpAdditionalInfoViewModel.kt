package com.example.it_da.ui.screen.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it_da.data.repository.AuthSessionRepository
import com.example.it_da.ui.screen.signup.state.SignUpAdditionalInfoNavigationEffect
import com.example.it_da.ui.screen.signup.state.SignUpAdditionalInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpAdditionalInfoViewModel @Inject constructor(
    private val authSessionRepository: AuthSessionRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpAdditionalInfoUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationEffect = MutableSharedFlow<SignUpAdditionalInfoNavigationEffect>()
    val navigationEffect = _navigationEffect.asSharedFlow()

    // Updates the user's display name for additional sign-up information.
    fun onNameChange(name: String) {
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }

    // Updates the selected or typed interest field value.
    fun onInterestFieldChange(interestField: String) {
        _uiState.update { currentState ->
            currentState.copy(interestField = interestField)
        }
    }

    // Updates the user's tech stack text for matching information.
    fun onTechStackChange(techStack: String) {
        _uiState.update { currentState ->
            currentState.copy(techStack = techStack)
        }
    }

    // Updates the cohort value shown in the additional information form.
    fun onCohortChange(cohort: String) {
        _uiState.update { currentState ->
            currentState.copy(cohort = cohort)
        }
    }

    // Updates the department value shown in the additional information form.
    fun onDepartmentChange(department: String) {
        _uiState.update { currentState ->
            currentState.copy(department = department)
        }
    }

    // Saves a temporary authenticated session after the additional sign-up information is complete.
    fun onNextClick() {
        if (!_uiState.value.isNextEnabled) {
            return
        }

        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isSessionSaving = true,
                    sessionErrorMessage = null
                )
            }

            authSessionRepository.saveTemporaryToken()
                .onSuccess {
                    _uiState.update { currentState ->
                        currentState.copy(isSessionSaving = false)
                    }
                    _navigationEffect.emit(SignUpAdditionalInfoNavigationEffect.NavigateToHome)
                }
                .onFailure { throwable ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isSessionSaving = false,
                            sessionErrorMessage = throwable.message ?: "로그인 상태 저장에 실패했습니다."
                        )
                    }
                }
        }
    }

    // Clears the session save error after the UI has shown it to the user.
    fun clearSessionError() {
        _uiState.update { currentState ->
            currentState.copy(sessionErrorMessage = null)
        }
    }
}
