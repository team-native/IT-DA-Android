package com.example.it_da.data.repository

import com.example.it_da.domain.model.ProjectCreateProject

interface ProjectCreateRepository {
    // Submits project creation values through the data layer boundary.
    suspend fun createProject(projectCreateProject: ProjectCreateProject): Result<Unit>
}
