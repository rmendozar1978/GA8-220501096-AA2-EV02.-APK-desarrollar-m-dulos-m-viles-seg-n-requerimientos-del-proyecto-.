package com.shieldtube.app.core.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

interface AppPreferencesRepository {
    val preferencesFlow: Flow<AppPreferences>
    suspend fun setVpnEnabled(enabled: Boolean)
    suspend fun setUpstreamDns(dns: String)
    suspend fun setAutoUpdateLists(enabled: Boolean)
    suspend fun setRemoteSelectorsUrl(url: String)
    suspend fun incrementBlockedQueries(count: Long = 1)
    suspend fun setLastListsUpdateTimestamp(timestamp: Long)
}

@Singleton
class DefaultAppPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppPreferencesRepository {

    private object PreferencesKeys {
        val IS_VPN_ENABLED = booleanPreferencesKey("is_vpn_enabled")
        val UPSTREAM_DNS = stringPreferencesKey("upstream_dns")
        val AUTO_UPDATE_LISTS = booleanPreferencesKey("auto_update_lists")
        val LAST_LISTS_UPDATE = longPreferencesKey("last_lists_update_timestamp")
        val REMOTE_SELECTORS_URL = stringPreferencesKey("remote_selectors_url")
        val TOTAL_BLOCKED_QUERIES = longPreferencesKey("total_blocked_queries")
    }

    override val preferencesFlow: Flow<AppPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            AppPreferences(
                isVpnEnabled = preferences[PreferencesKeys.IS_VPN_ENABLED] ?: false,
                upstreamDns = preferences[PreferencesKeys.UPSTREAM_DNS] ?: AppPreferences.DEFAULT_UPSTREAM_DNS,
                autoUpdateLists = preferences[PreferencesKeys.AUTO_UPDATE_LISTS] ?: true,
                lastListsUpdateTimestamp = preferences[PreferencesKeys.LAST_LISTS_UPDATE] ?: 0L,
                remoteSelectorsUrl = preferences[PreferencesKeys.REMOTE_SELECTORS_URL] ?: AppPreferences.DEFAULT_SELECTORS_URL,
                totalBlockedQueries = preferences[PreferencesKeys.TOTAL_BLOCKED_QUERIES] ?: 0L
            )
        }

    override suspend fun setVpnEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_VPN_ENABLED] = enabled
        }
    }

    override suspend fun setUpstreamDns(dns: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.UPSTREAM_DNS] = dns.trim()
        }
    }

    override suspend fun setAutoUpdateLists(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.AUTO_UPDATE_LISTS] = enabled
        }
    }

    override suspend fun setRemoteSelectorsUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.REMOTE_SELECTORS_URL] = url.trim()
        }
    }

    override suspend fun incrementBlockedQueries(count: Long) {
        dataStore.edit { preferences ->
            val current = preferences[PreferencesKeys.TOTAL_BLOCKED_QUERIES] ?: 0L
            preferences[PreferencesKeys.TOTAL_BLOCKED_QUERIES] = current + count
        }
    }

    override suspend fun setLastListsUpdateTimestamp(timestamp: Long) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_LISTS_UPDATE] = timestamp
        }
    }
}
