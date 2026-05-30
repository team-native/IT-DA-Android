package com.example.it_da.data.repository

import com.example.it_da.data.local.AuthTokenLocalDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultAuthSessionRepositoryTest {
    @Test
    fun hasStoredTokenReturnsTrueWhenLocalTokenIsNotBlank() = runTest {
        val repository = DefaultAuthSessionRepository(
            FakeAuthTokenLocalDataSource(storedToken = "stored-token")
        )

        assertTrue(repository.hasStoredToken().getOrThrow())
    }

    @Test
    fun hasStoredTokenReturnsFalseWhenLocalTokenIsBlank() = runTest {
        val repository = DefaultAuthSessionRepository(
            FakeAuthTokenLocalDataSource(storedToken = "")
        )

        assertFalse(repository.hasStoredToken().getOrThrow())
    }

    @Test
    fun saveTemporaryTokenStoresNonBlankPlaceholderToken() = runTest {
        val localDataSource = FakeAuthTokenLocalDataSource()
        val repository = DefaultAuthSessionRepository(localDataSource)

        repository.saveTemporaryToken().getOrThrow()

        assertTrue(localDataSource.storedToken?.isNotBlank() == true)
    }

    @Test
    fun clearTokenRemovesStoredToken() = runTest {
        val localDataSource = FakeAuthTokenLocalDataSource(storedToken = "stored-token")
        val repository = DefaultAuthSessionRepository(localDataSource)

        repository.clearToken().getOrThrow()

        assertEquals(null, localDataSource.storedToken)
    }

    @Test
    fun localStorageFailureIsReturnedToCaller() = runTest {
        val repository = DefaultAuthSessionRepository(
            FakeAuthTokenLocalDataSource(readFailure = IllegalStateException("Read failed"))
        )

        assertEquals("Read failed", repository.hasStoredToken().exceptionOrNull()?.message)
    }

    private class FakeAuthTokenLocalDataSource(
        var storedToken: String? = null,
        private val readFailure: Throwable? = null
    ) : AuthTokenLocalDataSource {
        override suspend fun getToken(): String? {
            readFailure?.let { throwable ->
                throw throwable
            }
            return storedToken
        }

        override suspend fun saveToken(token: String) {
            storedToken = token
        }

        override suspend fun clearToken() {
            storedToken = null
        }
    }
}
