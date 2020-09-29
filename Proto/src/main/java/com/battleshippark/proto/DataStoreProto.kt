package com.battleshippark.proto

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreProto(context: Context) {
    private val dataStore: DataStore<Settings> = context.createDataStore(
        fileName = "settings.pb",
        serializer = SettingsSerializer
    )

    fun read(): Flow<Int> {
        return dataStore.data
            .map { settings ->
                // No type safety.
                settings.counter
            }
    }

    fun inc() {
        GlobalScope.launch {
            dataStore.updateData { settings ->
                settings.toBuilder().setCounter(settings.counter + 1).build()
            }
        }
    }
}