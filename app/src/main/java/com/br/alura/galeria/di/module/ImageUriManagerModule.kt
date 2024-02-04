package com.br.alura.galeria.di.module

import android.content.Context
import com.br.alura.galeria.ImageUriManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ImageUriManagerModule {

    @Singleton
    @Provides
    fun provideImageUriManager(
        @ApplicationContext context: Context
    ): ImageUriManager {
        return ImageUriManager(context)
    }
}