package com.br.alura.galeria.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.alura.galeria.ImageUriManager
import com.br.alura.galeria.ui.splashScreen.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val imageUriManager: ImageUriManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            imageUriManager.getImageUris().let { images ->
                if (images.isNotEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        imageUris = images
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        goToSelectionFolder = true
                    )
                }
            }
        }
    }

    fun toggleScreen(isGalleryAligned: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                currentScreen = if (isGalleryAligned) Route.GALLERY else Route.IA,
                defaultAlignedGallery = isGalleryAligned
            )
        }
    }

    fun toggleSwitchButtonVisibility(visible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                showSwitchButton = visible
            )
        }
    }
}