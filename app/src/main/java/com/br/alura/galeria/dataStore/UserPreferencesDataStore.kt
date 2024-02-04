package com.br.alura.galeria.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    object PreferencesKey {
        val lastSelectedPath = stringPreferencesKey("last_selected_path")
    }

    suspend fun saveLastSelectedPath(path: String) {
        dataStore.edit { edit ->
            edit[PreferencesKey.lastSelectedPath] = path
        }
    }

    suspend fun getLastSelectedPath(): String? {
        return dataStore.data.map {
            it[PreferencesKey.lastSelectedPath]
        }.first()
    }
}