package com.shieldtube.app.core.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class AppPreferencesRepositoryTest {

    private val dataStore: DataStore<Preferences> = mockk(relaxed = true)
    private lateinit var repository: DefaultAppPreferencesRepository

    @Before
    fun setup() {
        repository = DefaultAppPreferencesRepository(dataStore)
    }

    @Test
    fun `preferencesFlow emits default values when datastore is empty`() = runTest {
        val emptyPrefs = preferencesOf()
        every { dataStore.data } returns flowOf(emptyPrefs)

        repository.preferencesFlow.test {
            val item = awaitItem()
            assertFalse(item.isVpnEnabled)
            assertEquals("1.1.1.1", item.upstreamDns)
            assertEquals(0L, item.totalBlockedQueries)
            awaitComplete()
        }
    }
}
