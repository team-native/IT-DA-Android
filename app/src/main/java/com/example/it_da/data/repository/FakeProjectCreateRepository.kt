package com.example.it_da.data.repository

import com.example.it_da.domain.model.ProjectCreateProject
import com.example.it_da.data.store.ProjectStore
import com.example.it_da.domain.model.ParticipatingProject
import javax.inject.Inject

class FakeProjectCreateRepository @Inject constructor(
    private val projectStore: ProjectStore
) : ProjectCreateRepository {
    private var nextCreatedProjectId = 1

    // Adds a fake server-created project to shared Store state until the real endpoint is connected.
    override suspend fun createProject(projectCreateProject: ProjectCreateProject): Result<Unit> {
        projectStore.addCreatedProject(
            ParticipatingProject(
                id = "created-project-${nextCreatedProjectId++}",
                title = projectCreateProject.projectName,
                myRole = "내 역할 : 프로젝트 생성자",
                statusText = "모집 중",
                teamSummary = "팀원 1명ㆍ마감 ${projectCreateProject.deadline}"
            )
        )
        return Result.success(Unit)
    }
}
