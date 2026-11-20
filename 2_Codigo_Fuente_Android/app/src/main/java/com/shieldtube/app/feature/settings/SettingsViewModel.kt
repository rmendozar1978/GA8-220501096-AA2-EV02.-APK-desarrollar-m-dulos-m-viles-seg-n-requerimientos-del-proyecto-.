package com.shieldtube.app.feature.settings

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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class SettingsUiState(
    val upstreamDns: String = AppPreferences.DEFAULT_UPSTREAM_DNS,
    val autoUpdateLists: Boolean = true,
    val remoteSelectorsUrl: String = AppPreferences.DEFAULT_SELECTORS_URL,
    val lastUpdateFormatted: String = "Nunca"
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: AppPreferencesRepository
) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> = preferencesRepository.preferencesFlow
        .map { prefs ->
            val formattedDate = if (prefs.lastListsUpdateTimestamp > 0) {
                SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    .format(Date(prefs.lastListsUpdateTimestamp))
            } else {
                "Nunca"
            }

            SettingsUiState(
                upstreamDns = prefs.upstreamDns,
                autoUpdateLists = prefs.autoUpdateLists,
                remoteSelectorsUrl = prefs.remoteSelectorsUrl,
                lastUpdateFormatted = formattedDate
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsUiState()
        )

    fun updateUpstreamDns(dns: String) {
        viewModelScope.launch {
            preferencesRepository.setUpstreamDns(dns)
        }
    }

    fun toggleAutoUpdate(enable: Boolean) {
        viewModelScope.launch {
            preferencesRepository.setAutoUpdateLists(enable)
        }
    }

    fun updateRemoteSelectorsUrl(url: String) {
        viewModelScope.launch {
            preferencesRepository.setRemoteSelectorsUrl(url)
        }
    }
}
