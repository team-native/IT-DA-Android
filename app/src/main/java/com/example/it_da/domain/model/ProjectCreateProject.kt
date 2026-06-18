package com.example.it_da.domain.model

data class ProjectCreateProject(
    val projectName: String,
    val category: String,
    val period: String,
    val method: String,
    val introduction: String,
    val goal: String,
    val memberCount: String,
    val role: String,
    val techStack: String,
    val deadline: String
)
