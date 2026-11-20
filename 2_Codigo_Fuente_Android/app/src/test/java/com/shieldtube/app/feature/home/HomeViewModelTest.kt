package com.shieldtube.app.feature.home

import app.cash.turbine.test
import com.shieldtube.app.core.data.preferences.AppPreferences
import com.shieldtube.app.core.data.preferences.AppPreferencesRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val preferencesRepository: AppPreferencesRepository = mockk(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()
    private val preferencesFlow = MutableStateFlow(AppPreferences())

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { preferencesRepository.preferencesFlow } returns preferencesFlow
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState emits initial state reflecting preferences`() = runTest {
        val viewModel = HomeViewModel(preferencesRepository)
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.test {
            val item = awaitItem()
            assertFalse(item.isVpnActive)
            assertEquals("1.1.1.1", item.upstreamDns)
            assertEquals(0L, item.blockedQueriesCount)
        }
    }

    @Test
    fun `toggleVpn calls repository setVpnEnabled`() = runTest {
        val viewModel = HomeViewModel(preferencesRepository)
        viewModel.toggleVpn(true)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { preferencesRepository.setVpnEnabled(true) }
    }
}
