package com.br.alura.galeria.ui.gallery

import com.br.alura.galeria.data.ImageGroup
import com.br.alura.galeria.data.ImageWithLabels

data class ImageGalleryUiState(
    val imageUris: List<ImageWithLabels> = emptyList(),
    val groups: List<ImageGroup> = emptyList(),
    val isLoading: Boolean = true,
    val hasSelectedImage: Boolean = false,
    val selectedImage: ImageWithLabels? = null,
)
