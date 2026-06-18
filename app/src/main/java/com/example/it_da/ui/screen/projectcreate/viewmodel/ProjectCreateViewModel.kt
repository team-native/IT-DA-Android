package com.example.it_da.ui.screen.projectcreate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it_da.data.repository.ProjectCreateRepository
import com.example.it_da.ui.screen.projectcreate.toProjectCreateProject
import com.example.it_da.ui.screen.projectcreate.state.ProjectCreateUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProjectCreateViewModel @Inject constructor(
    private val projectCreateRepository: ProjectCreateRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProjectCreateUiState())
    val uiState = _uiState.asStateFlow()

    // Updates the project name typed in the basic information section.
    fun onProjectNameChange(projectName: String) {
        _uiState.update { currentState ->
            currentState.copy(projectName = projectName, isSubmitSuccess = false)
        }
    }

    // Updates the category value shown in the category field.
    fun onCategoryChange(category: String) {
        _uiState.update { currentState ->
            currentState.copy(category = category, isSubmitSuccess = false)
        }
    }

    // Updates the expected project period value.
    fun onPeriodChange(period: String) {
        _uiState.update { currentState ->
            currentState.copy(period = period, isSubmitSuccess = false)
        }
    }

    // Updates how the project team plans to work.
    fun onMethodChange(method: String) {
        _uiState.update { currentState ->
            currentState.copy(method = method, isSubmitSuccess = false)
        }
    }

    // Updates the project introduction text.
    fun onIntroductionChange(introduction: String) {
        _uiState.update { currentState ->
            currentState.copy(introduction = introduction, isSubmitSuccess = false)
        }
    }

    // Updates the goal and expected result text.
    fun onGoalChange(goal: String) {
        _uiState.update { currentState ->
            currentState.copy(goal = goal, isSubmitSuccess = false)
        }
    }

    // Updates the number of people the project wants to recruit.
    fun onMemberCountChange(memberCount: String) {
        _uiState.update { currentState ->
            currentState.copy(memberCount = memberCount, isSubmitSuccess = false)
        }
    }

    // Updates the role the project wants to recruit.
    fun onRoleChange(role: String) {
        _uiState.update { currentState ->
            currentState.copy(role = role, isSubmitSuccess = false)
        }
    }

    // Updates the technology stack required for applicants.
    fun onTechStackChange(techStack: String) {
        _uiState.update { currentState ->
            currentState.copy(techStack = techStack, isSubmitSuccess = false)
        }
    }

    // Updates the application deadline shown in the recruitment section.
    fun onDeadlineChange(deadline: String) {
        _uiState.update { currentState ->
            currentState.copy(deadline = deadline, isSubmitSuccess = false)
        }
    }

    // Sends the current form values to the repository and exposes the submit result to the route.
    fun submitProject() {
        val currentState = _uiState.value
        if (!currentState.isSubmitEnabled) {
            return
        }

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isSubmitting = true, errorMessage = null, isSubmitSuccess = false)
            }

            projectCreateRepository.createProject(currentState.toProjectCreateProject())
                .onSuccess {
                    _uiState.update { state ->
                        state.copy(isSubmitting = false, isSubmitSuccess = true)
                    }
                }
                .onFailure { throwable ->
                    _uiState.update { state ->
                        state.copy(
                            isSubmitting = false,
                            errorMessage = throwable.message ?: "프로젝트 등록에 실패했습니다."
                        )
                    }
                }
        }
    }
}
