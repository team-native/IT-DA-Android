package com.example.it_da.ui.screen.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it_da.data.repository.HomeRepository
import com.example.it_da.ui.screen.home.toHomeUiState
import com.example.it_da.ui.screen.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    val uiState = homeRepository.homeDashboard
        .map { homeDashboard ->
            homeDashboard.toHomeUiState()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = HomeUiState()
        )

    init {
        refreshHomeDashboard()
    }

    // Requests dashboard refresh while ongoing Store changes continue to update UI state.
    private fun refreshHomeDashboard() {
        viewModelScope.launch {
            homeRepository.refreshDashboard()
        }
    }
}
