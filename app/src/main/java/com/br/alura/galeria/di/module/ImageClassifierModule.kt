package com.br.alura.galeria.di.module

import android.content.Context
import com.br.alura.galeria.mlkit.ImageClassifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ImageClassifierModule {

    @Singleton
    @Provides
    fun provideImageClassifier(
        @ApplicationContext context: Context
    ): ImageClassifier {
        return ImageClassifier(context)
    }
}