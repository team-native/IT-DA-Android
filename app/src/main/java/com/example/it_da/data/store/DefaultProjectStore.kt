package com.example.it_da.data.store

import com.example.it_da.domain.model.ParticipatingProject
import com.example.it_da.domain.model.ProjectStoreState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DefaultProjectStore @Inject constructor() : ProjectStore {
    private val _state = MutableStateFlow(ProjectStoreState())
    override val state = _state.asStateFlow()

    // Publishes a complete project snapshot so all project consumers stay consistent.
    override fun replaceState(state: ProjectStoreState) {
        _state.value = state
    }

    // Prepends a created project and increments the participating count in one state update.
    override fun addCreatedProject(project: ParticipatingProject) {
        _state.update { currentState ->
            currentState.copy(
                projectCount = currentState.projectCount.copy(
                    participatingCount = currentState.projectCount.participatingCount + 1
                ),
                participatingProjects = listOf(project) + currentState.participatingProjects
            )
        }
    }
}
