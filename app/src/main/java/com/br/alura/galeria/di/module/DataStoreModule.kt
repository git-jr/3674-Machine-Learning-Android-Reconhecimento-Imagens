package com.br.alura.galeria.di.module

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.br.alura.galeria.dataStore.UserPreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val GALERIA_IA_CACHE_DATASTORE = "galeria_ia_cache_datastore"

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStoreUserPreferences(@ApplicationContext context: Context): UserPreferencesDataStore {
        val dataStore = PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(
                    GALERIA_IA_CACHE_DATASTORE
                )
            }
        )
        return UserPreferencesDataStore(dataStore)
    }
}

