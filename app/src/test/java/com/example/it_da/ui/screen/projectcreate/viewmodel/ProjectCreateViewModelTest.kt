package com.example.it_da.ui.screen.projectcreate.viewmodel

import com.example.it_da.data.repository.FakeProjectCreateRepository
import com.example.it_da.data.repository.ProjectCreateRepository
import com.example.it_da.data.store.DefaultProjectStore
import com.example.it_da.domain.model.ProjectCount
import com.example.it_da.domain.model.ProjectCreateProject
import com.example.it_da.domain.model.ProjectStoreState
import com.example.it_da.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProjectCreateViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun updatesProjectCreateFormValuesInUiState() {
        val viewModel = ProjectCreateViewModel(SuccessProjectCreateRepository())

        viewModel.onProjectNameChange("새 프로젝트")
        viewModel.onCategoryChange("앱 개발")
        viewModel.onPeriodChange("2개월 이내")
        viewModel.onMethodChange("온라인")
        viewModel.onIntroductionChange("프로젝트 소개")
        viewModel.onGoalChange("목표")
        viewModel.onMemberCountChange("1명")
        viewModel.onRoleChange("프론트엔드")
        viewModel.onTechStackChange("Kotlin")
        viewModel.onDeadlineChange("2026-06-30")

        val uiState = viewModel.uiState.value
        assertEquals("새 프로젝트", uiState.projectName)
        assertEquals("앱 개발", uiState.category)
        assertEquals("2개월 이내", uiState.period)
        assertEquals("온라인", uiState.method)
        assertEquals("프로젝트 소개", uiState.introduction)
        assertEquals("목표", uiState.goal)
        assertEquals("1명", uiState.memberCount)
        assertEquals("프론트엔드", uiState.role)
        assertEquals("Kotlin", uiState.techStack)
        assertEquals("2026-06-30", uiState.deadline)
    }

    @Test
    fun submitProjectMarksSuccessWhenRepositorySucceeds() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val viewModel = ProjectCreateViewModel(SuccessProjectCreateRepository())

        viewModel.onProjectNameChange("새 프로젝트")
        viewModel.onCategoryChange("앱 개발")
        viewModel.onPeriodChange("2개월 이내")
        viewModel.onMethodChange("온라인")
        viewModel.onIntroductionChange("프로젝트 소개")
        viewModel.onGoalChange("목표")
        viewModel.onMemberCountChange("1명")
        viewModel.onRoleChange("프론트엔드")
        viewModel.onTechStackChange("Kotlin")
        viewModel.onDeadlineChange("2026-06-30")
        viewModel.submitProject()
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.isSubmitSuccess)
    }

    @Test
    fun submitProjectPublishesCreatedProjectToSharedStore() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val projectStore = DefaultProjectStore().apply {
            replaceState(
                ProjectStoreState(
                    projectCount = ProjectCount(
                        applyingCount = 3,
                        participatingCount = 1,
                        completedCount = 1
                    )
                )
            )
        }
        val viewModel = ProjectCreateViewModel(FakeProjectCreateRepository(projectStore))

        viewModel.onProjectNameChange("새 프로젝트")
        viewModel.onCategoryChange("앱 개발")
        viewModel.onPeriodChange("2개월 이내")
        viewModel.onMethodChange("온라인")
        viewModel.onIntroductionChange("프로젝트 소개")
        viewModel.onGoalChange("목표")
        viewModel.onMemberCountChange("1명")
        viewModel.onRoleChange("프론트엔드")
        viewModel.onTechStackChange("Kotlin")
        viewModel.onDeadlineChange("2026-06-30")
        viewModel.submitProject()
        advanceUntilIdle()

        val createdProject = projectStore.state.value.participatingProjects.first()
        assertEquals(2, projectStore.state.value.projectCount.participatingCount)
        assertEquals("새 프로젝트", createdProject.title)
        assertEquals("내 역할 : 프로젝트 생성자", createdProject.myRole)
        assertEquals("모집 중", createdProject.statusText)
        assertEquals("팀원 1명ㆍ마감 2026-06-30", createdProject.teamSummary)
    }

    private class SuccessProjectCreateRepository : ProjectCreateRepository {
        // Returns success so the ViewModel submit state can be verified without network access.
        override suspend fun createProject(
            projectCreateProject: ProjectCreateProject
        ): Result<Unit> {
            return Result.success(Unit)
        }
    }
}
