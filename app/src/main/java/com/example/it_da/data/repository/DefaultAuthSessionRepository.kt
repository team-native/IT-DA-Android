package com.example.it_da.data.repository

import com.example.it_da.data.local.AuthTokenLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

private const val TemporaryAuthToken = "temporary-auth-token"

@Singleton
class DefaultAuthSessionRepository @Inject constructor(
    private val authTokenLocalDataSource: AuthTokenLocalDataSource
) : AuthSessionRepository {
    // Treats any non-empty local token as an authenticated session until server validation is connected.
    override suspend fun hasStoredToken(): Result<Boolean> {
        return runCatching {
            !authTokenLocalDataSource.getToken().isNullOrBlank()
        }
    }

    // Persists a placeholder token so the pre-server authentication flow can be verified end to end.
    override suspend fun saveTemporaryToken(): Result<Unit> {
        return runCatching {
            authTokenLocalDataSource.saveToken(TemporaryAuthToken)
        }
    }

    // Removes the current token behind the repository boundary for the future logout UI.
    override suspend fun clearToken(): Result<Unit> {
        return runCatching {
            authTokenLocalDataSource.clearToken()
        }
    }
}
