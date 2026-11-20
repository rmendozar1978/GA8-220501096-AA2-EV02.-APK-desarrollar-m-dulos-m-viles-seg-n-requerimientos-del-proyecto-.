package com.shieldtube.app.core.data.preferences

/**
 * Modelo inmutable de preferencias globales de ShieldTube.
 */
data class AppPreferences(
    val isVpnEnabled: Boolean = false,
    val upstreamDns: String = DEFAULT_UPSTREAM_DNS,
    val autoUpdateLists: Boolean = true,
    val lastListsUpdateTimestamp: Long = 0L,
    val remoteSelectorsUrl: String = DEFAULT_SELECTORS_URL,
    val totalBlockedQueries: Long = 0L
) {
    companion object {
        const val DEFAULT_UPSTREAM_DNS = "1.1.1.1" // Cloudflare DNS
        const val DEFAULT_SELECTORS_URL = "https://raw.githubusercontent.com/shieldtube/rules/main/selectors.json"
    }
}
