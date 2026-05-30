package com.example.it_da.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DefaultAuthTokenLocalDataSourceTest {
    private val localDataSource = DefaultAuthTokenLocalDataSource(
        InstrumentationRegistry.getInstrumentation().targetContext
    )

    @Before
    fun clearTokenBeforeTest() = runBlocking {
        localDataSource.clearToken()
    }

    @After
    fun clearTokenAfterTest() = runBlocking {
        localDataSource.clearToken()
    }

    @Test
    fun savesLoadsAndClearsTokenInPreferencesDataStore() = runBlocking {
        localDataSource.saveToken("stored-token")

        assertEquals("stored-token", localDataSource.getToken())

        localDataSource.clearToken()

        assertNull(localDataSource.getToken())
    }
}
