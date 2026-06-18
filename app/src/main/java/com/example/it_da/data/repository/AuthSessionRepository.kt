package com.example.it_da.data.repository

interface AuthSessionRepository {
    // Checks whether a non-empty local token is available for automatic login.
    suspend fun hasStoredToken(): Result<Boolean>

    // Saves a temporary token until the server authentication contract is connected.
    suspend fun saveTemporaryToken(): Result<Unit>

    // Clears the stored token for the future logout flow.
    suspend fun clearToken(): Result<Unit>
}
