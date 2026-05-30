package com.example.it_da.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first

private const val AuthSessionDataStoreName = "auth_session"

private val Context.authSessionDataStore by preferencesDataStore(
    name = AuthSessionDataStoreName
)

@Singleton
class DefaultAuthTokenLocalDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context
) : AuthTokenLocalDataSource {
    // Loads the token from Preferences DataStore for the app launch session decision.
    override suspend fun getToken(): String? {
        return context.authSessionDataStore.data.first()[AuthTokenKey]
    }

    // Stores the token in Preferences DataStore after a temporary authentication success.
    override suspend fun saveToken(token: String) {
        context.authSessionDataStore.edit { preferences ->
            preferences[AuthTokenKey] = token
        }
    }

    // Deletes the token from Preferences DataStore for a future logout flow.
    override suspend fun clearToken() {
        context.authSessionDataStore.edit { preferences ->
            preferences.remove(AuthTokenKey)
        }
    }

    private companion object {
        val AuthTokenKey = stringPreferencesKey("auth_token")
    }
}
