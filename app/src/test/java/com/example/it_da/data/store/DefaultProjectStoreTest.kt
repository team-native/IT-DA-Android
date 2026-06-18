package com.example.it_da.data.store

import com.example.it_da.domain.model.ParticipatingProject
import com.example.it_da.domain.model.ProjectCount
import com.example.it_da.domain.model.ProjectStoreState
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultProjectStoreTest {
    @Test
    fun addCreatedProjectPrependsProjectAndIncrementsParticipatingCount() {
        val store = DefaultProjectStore()
        store.replaceState(
            ProjectStoreState(
                projectCount = ProjectCount(
                    applyingCount = 3,
                    participatingCount = 1,
                    completedCount = 1
                ),
                participatingProjects = listOf(
                    createParticipatingProject(id = "existing", title = "기존 프로젝트")
                )
            )
        )

        store.addCreatedProject(
            createParticipatingProject(id = "created", title = "새 프로젝트")
        )

        assertEquals(2, store.state.value.projectCount.participatingCount)
        assertEquals(
            listOf("created", "existing"),
            store.state.value.participatingProjects.map(ParticipatingProject::id)
        )
    }

    private fun createParticipatingProject(
        id: String,
        title: String
    ): ParticipatingProject {
        return ParticipatingProject(
            id = id,
            title = title,
            myRole = "내 역할",
            statusText = "진행 중",
            teamSummary = "팀 정보"
        )
    }
}
