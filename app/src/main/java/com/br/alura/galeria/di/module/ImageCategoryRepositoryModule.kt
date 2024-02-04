package com.br.alura.galeria.di.module

import com.br.alura.galeria.data.ImageCategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ImageCategoryRepositoryModule {

    @Provides
    fun provideImageCategoryRepository(): ImageCategoryRepository {
        return ImageCategoryRepository()
    }
}