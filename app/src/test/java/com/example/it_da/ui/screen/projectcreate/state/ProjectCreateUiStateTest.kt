package com.example.it_da.ui.screen.projectcreate.state

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ProjectCreateUiStateTest {
    @Test
    fun submitButtonDisabledWhenRequiredFieldIsEmpty() {
        val state = ProjectCreateUiState(
            projectName = "새 프로젝트",
            category = "앱 개발",
            period = "2개월 이내",
            method = "온라인",
            introduction = "프로젝트 소개",
            goal = "목표",
            memberCount = "1명",
            role = "프론트엔드",
            techStack = "Kotlin",
            deadline = ""
        )

        assertFalse(state.isSubmitEnabled)
    }

    @Test
    fun submitButtonEnabledWhenAllRequiredFieldsAreFilled() {
        val state = ProjectCreateUiState(
            projectName = "새 프로젝트",
            category = "앱 개발",
            period = "2개월 이내",
            method = "온라인",
            introduction = "프로젝트 소개",
            goal = "목표",
            memberCount = "1명",
            role = "프론트엔드",
            techStack = "Kotlin",
            deadline = "2026-06-30"
        )

        assertTrue(state.isSubmitEnabled)
    }
}
