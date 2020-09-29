package com.battleshippark.preference

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStorePreference(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = "settings"
    )

    fun read(): Flow<Int> {
        val counter = preferencesKey<Int>("example_counter")
        return dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[counter] ?: 0
            }
    }

    suspend fun inc() {
        GlobalScope.launch {
            dataStore.edit { settings ->
                val counter = preferencesKey<Int>("example_counter")
                val currentCounterValue = settings[counter] ?: 0
                settings[counter] = currentCounterValue + 1
            }
        }
    }
}