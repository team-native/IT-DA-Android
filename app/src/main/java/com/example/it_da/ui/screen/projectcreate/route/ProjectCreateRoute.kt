package com.example.it_da.ui.screen.projectcreate.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.it_da.ui.screen.projectcreate.screen.ProjectCreateScreen
import com.example.it_da.ui.screen.projectcreate.viewmodel.ProjectCreateViewModel

// Connects project creation state, form events, and navigation callbacks to the screen.
@Composable
fun ProjectCreateRoute(
    onBackClick: () -> Unit,
    onSubmitSuccess: () -> Unit,
    onHomeTabClick: () -> Unit,
    onExploreTabClick: () -> Unit,
    onCreateProjectClick: () -> Unit,
    onNotificationTabClick: () -> Unit,
    onProfileTabClick: () -> Unit,
    viewModel: ProjectCreateViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSubmitSuccess) {
        if (uiState.isSubmitSuccess) {
            onSubmitSuccess()
        }
    }

    ProjectCreateScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onProjectNameChange = viewModel::onProjectNameChange,
        onCategoryChange = viewModel::onCategoryChange,
        onPeriodChange = viewModel::onPeriodChange,
        onMethodChange = viewModel::onMethodChange,
        onIntroductionChange = viewModel::onIntroductionChange,
        onGoalChange = viewModel::onGoalChange,
        onMemberCountChange = viewModel::onMemberCountChange,
        onRoleChange = viewModel::onRoleChange,
        onTechStackChange = viewModel::onTechStackChange,
        onDeadlineChange = viewModel::onDeadlineChange,
        onDropdownArrowClick = {},
        onSubmitClick = viewModel::submitProject,
        onHomeTabClick = onHomeTabClick,
        onExploreTabClick = onExploreTabClick,
        onCreateProjectClick = onCreateProjectClick,
        onNotificationTabClick = onNotificationTabClick,
        onProfileTabClick = onProfileTabClick
    )
}
