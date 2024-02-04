package com.br.alura.galeria.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.alura.galeria.ImageUriManager
import com.br.alura.galeria.data.ImageWithLabels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageGalleryViewModel @Inject constructor(
    private val imageUriManager: ImageUriManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImageGalleryUiState())
    val uiState: StateFlow<ImageGalleryUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                imageUris = imageUriManager.getImageWithLabels(),
                isLoading = false
            )
        }
    }

    fun toggleSwitchButtonVisibility(isVisibility: Boolean) {
        _uiState.value = _uiState.value.copy(
            hasSelectedImage = isVisibility
        )
    }

    fun setSelectedImage(image: ImageWithLabels) {
        _uiState.value = _uiState.value.copy(
            selectedImage = image,
            hasSelectedImage = true
        )
    }
}