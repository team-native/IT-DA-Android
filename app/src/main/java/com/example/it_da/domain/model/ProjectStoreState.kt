package com.example.it_da.domain.model

// Groups project values so Store updates publish one consistent project snapshot.
data class ProjectStoreState(
    val projectCount: ProjectCount = ProjectCount(
        applyingCount = 0,
        participatingCount = 0,
        completedCount = 0
    ),
    val recommendedProjects: List<RecommendedProject> = emptyList(),
    val participatingProjects: List<ParticipatingProject> = emptyList()
)
