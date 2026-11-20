package com.shieldtube.app.feature.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shieldtube.app.core.data.preferences.AppPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class YouTubeUiState(
    val url: String = "https://m.youtube.com",
    val isLoading: Boolean = false,
    val isAdBlockingActive: Boolean = true,
    val skippedAdsCount: Int = 0
)

@HiltViewModel
class YouTubeViewModel @Inject constructor(
    private val preferencesRepository: AppPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(YouTubeUiState())
    val uiState: StateFlow<YouTubeUiState> = _uiState.asStateFlow()

    fun updateLoadingState(loading: Boolean) {
        _uiState.update { it.copy(isLoading = loading) }
    }

    fun incrementSkippedAds() {
        _uiState.update { it.copy(skippedAdsCount = it.skippedAdsCount + 1) }
        viewModelScope.launch {
            preferencesRepository.incrementBlockedQueries(1)
        }
    }
}
