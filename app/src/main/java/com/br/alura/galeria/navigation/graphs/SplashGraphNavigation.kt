package com.br.alura.galeria.navigation.graphs

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.br.alura.galeria.navigation.Destinations
import com.br.alura.galeria.ui.splashScreen.AppState
import com.br.alura.galeria.ui.splashScreen.SplashScreen
import com.br.alura.galeria.ui.splashScreen.SplashViewModel

fun NavGraphBuilder.splashGraph(
    onNavigateHome: () -> Unit = {},
    onNavigateSelectFolder: () -> Unit = {},
) {
    composable(
        route = Destinations.SplashScreen.route
    ) {
        val viewModel = hiltViewModel<SplashViewModel>()
        val state by viewModel.uiState.collectAsState()

        LaunchedEffect(state.appState) {
            if (state.appState == AppState.Loaded) onNavigateHome()
            if (state.appState == AppState.FirstTime) onNavigateSelectFolder()
        }

        Crossfade(
            state.appState,
            label = "Splash Screen"
        ) { splashState ->
            if (splashState == AppState.Loading) {
                SplashScreen()
            }
        }
    }
}

