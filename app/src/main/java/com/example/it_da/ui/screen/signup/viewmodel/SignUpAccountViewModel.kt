package com.example.it_da.ui.screen.signup.viewmodel

import androidx.lifecycle.ViewModel
import com.example.it_da.ui.screen.signup.state.SignUpAccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SignUpAccountViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpAccountUiState())
    val uiState = _uiState.asStateFlow()

    // Updates the account id when the user types in the id field.
    fun onIdChange(id: String) {
        _uiState.update { currentState ->
            currentState.copy(id = id)
        }
    }

    // Updates the password value that is used for the first sign-up validation step.
    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    // Updates the confirmation password so the screen can validate both password fields.
    fun onPasswordConfirmChange(passwordConfirm: String) {
        _uiState.update { currentState ->
            currentState.copy(passwordConfirm = passwordConfirm)
        }
    }
}
