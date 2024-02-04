package com.br.alura.galeria.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.br.alura.galeria.navigation.Destinations
import com.br.alura.galeria.ui.components.LoadScreen
import com.br.alura.galeria.ui.homeScreen.HomeScreen
import com.br.alura.galeria.ui.homeScreen.HomeScreenViewModel

fun NavGraphBuilder.homeGraph(
    onChangeFolder: () -> Unit = {},
) {
    navigation(
        startDestination = Destinations.HomeScreen.route,
        route = Destinations.HomeGraph.route
    ) {
        composable(
            route = Destinations.HomeScreen.route
        ) {
            val viewModel = hiltViewModel<HomeScreenViewModel>()
            val state by viewModel.uiState.collectAsState()

            if (state.imageUris.isEmpty()) {
                LoadScreen()
            } else {
                HomeScreen {
                    onChangeFolder()
                }
            }

            if (state.goToSelectionFolder) {
                onChangeFolder()
            }
        }
    }
}

