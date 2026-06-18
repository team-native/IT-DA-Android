package com.example.it_da.data.repository

import com.example.it_da.domain.model.HomeDashboard
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    val homeDashboard: Flow<HomeDashboard>

    // Refreshes shared Store values from the dashboard source without exposing data sources to UI.
    suspend fun refreshDashboard(): Result<Unit>
}
