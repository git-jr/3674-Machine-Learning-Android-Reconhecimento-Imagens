package com.br.alura.galeria.ui.homeScreen

import com.br.alura.galeria.ui.splashScreen.Route

data class HomeScreenUiState(
    val path: String? = null,
    val imageUris: List<String> = emptyList(),
    val goToSelectionFolder: Boolean = false,
    val currentScreen: Route = Route.GALLERY,
    val defaultAlignedGallery: Boolean = true,
    val showSwitchButton: Boolean = true,
)
