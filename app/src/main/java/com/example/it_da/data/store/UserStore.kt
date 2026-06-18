package com.example.it_da.data.store

import com.example.it_da.domain.model.UserSummary
import kotlinx.coroutines.flow.StateFlow

interface UserStore {
    val userSummary: StateFlow<UserSummary>

    // Replaces shared user summary values after a repository loads authoritative data.
    fun replaceUserSummary(userSummary: UserSummary)
}
