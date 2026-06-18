package com.example.it_da.ui.screen.projectcreate

import com.example.it_da.domain.model.ProjectCreateProject
import com.example.it_da.ui.screen.projectcreate.state.ProjectCreateUiState

// Converts form UI state into the app-level project model used by the repository.
fun ProjectCreateUiState.toProjectCreateProject(): ProjectCreateProject {
    return ProjectCreateProject(
        projectName = projectName,
        category = category,
        period = period,
        method = method,
        introduction = introduction,
        goal = goal,
        memberCount = memberCount,
        role = role,
        techStack = techStack,
        deadline = deadline
    )
}
