package com.shieldtube.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shieldtube.app.core.data.preferences.AppPreferences
import com.shieldtube.app.core.data.preferences.AppPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isVpnActive: Boolean = false,
    val blockedQueriesCount: Long = 0L,
    val upstreamDns: String = AppPreferences.DEFAULT_UPSTREAM_DNS,
    val isLoading: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesRepository: AppPreferencesRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = preferencesRepository.preferencesFlow
        .map { prefs ->
            HomeUiState(
                isVpnActive = prefs.isVpnEnabled,
                blockedQueriesCount = prefs.totalBlockedQueries,
                upstreamDns = prefs.upstreamDns,
                isLoading = false
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState()
        )

    fun toggleVpn(enable: Boolean) {
        viewModelScope.launch {
            preferencesRepository.setVpnEnabled(enable)
        }
    }
}
