package com.example.it_da.data.local

interface AuthTokenLocalDataSource {
    // Loads the locally stored authentication token used by the launch session check.
    suspend fun getToken(): String?

    // Persists the authentication token so the next app launch can restore the session.
    suspend fun saveToken(token: String)

    // Removes the locally stored authentication token when the session should end.
    suspend fun clearToken()
}
