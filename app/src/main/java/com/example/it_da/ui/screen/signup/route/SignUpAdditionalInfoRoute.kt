package com.example.it_da.ui.screen.signup.route

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.it_da.ui.screen.signup.screen.SignUpAdditionalInfoScreen
import com.example.it_da.ui.screen.signup.state.SignUpAdditionalInfoNavigationEffect
import com.example.it_da.ui.screen.signup.viewmodel.SignUpAdditionalInfoViewModel

// Connects additional sign-up state and events to the second sign-up screen.
@Composable
fun SignUpAdditionalInfoRoute(
    onSignUpSuccess: () -> Unit,
    viewModel: SignUpAdditionalInfoViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val sessionErrorMessage = uiState.sessionErrorMessage

    LaunchedEffect(viewModel) {
        viewModel.navigationEffect.collect { effect ->
            when (effect) {
                SignUpAdditionalInfoNavigationEffect.NavigateToHome -> onSignUpSuccess()
            }
        }
    }

    LaunchedEffect(sessionErrorMessage) {
        if (sessionErrorMessage != null) {
            Toast.makeText(context, sessionErrorMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearSessionError()
        }
    }

    SignUpAdditionalInfoScreen(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onInterestFieldChange = viewModel::onInterestFieldChange,
        onTechStackChange = viewModel::onTechStackChange,
        onCohortChange = viewModel::onCohortChange,
        onDepartmentChange = viewModel::onDepartmentChange,
        onDropdownArrowClick = {},
        onNextClick = viewModel::onNextClick
    )
}
