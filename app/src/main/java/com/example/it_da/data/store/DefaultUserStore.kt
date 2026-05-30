package com.example.it_da.data.store

import com.example.it_da.domain.model.UserSummary
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultUserStore @Inject constructor() : UserStore {
    private val _userSummary = MutableStateFlow(
        UserSummary(
            userName = "",
            greetingDescription = ""
        )
    )
    override val userSummary = _userSummary.asStateFlow()

    // Publishes the latest user summary as the shared in-memory value.
    override fun replaceUserSummary(userSummary: UserSummary) {
        _userSummary.value = userSummary
    }
}
