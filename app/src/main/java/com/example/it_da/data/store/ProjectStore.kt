package com.example.it_da.data.store

import com.example.it_da.domain.model.ParticipatingProject
import com.example.it_da.domain.model.ProjectStoreState
import kotlinx.coroutines.flow.StateFlow

interface ProjectStore {
    val state: StateFlow<ProjectStoreState>

    // Replaces all shared project values after a repository refresh succeeds.
    fun replaceState(state: ProjectStoreState)

    // Adds a newly created project to the current user's participating projects.
    fun addCreatedProject(project: ParticipatingProject)
}
