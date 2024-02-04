package com.br.alura.galeria.ui.splashScreen

data class SplashScreenUiState(
    val appState: AppState = AppState.Loading,
    val path: String? = null,
    val route: Route = Route.LOADING
)

sealed class AppState {
    data object Loading : AppState()
    data object Loaded : AppState()
    data object FirstTime : AppState()
}

enum class Route {
    LOADING,
    GALLERY,
    IA
}