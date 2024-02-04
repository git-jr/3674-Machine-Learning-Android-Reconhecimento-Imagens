package com.br.alura.galeria.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.br.alura.galeria.navigation.Destinations
import com.br.alura.galeria.ui.selectionFolderScreen.SelectionFolderScreen

fun NavGraphBuilder.folderGraph(
    onSelectedFolder: () -> Unit = {},
    onDetailImage: () -> Unit = {}
) {
    navigation(
        startDestination = Destinations.SelectionFolder.route,
        route = Destinations.FolderGraph.route
    ) {
        composable(
            route = Destinations.SelectionFolder.route
        ) {
            SelectionFolderScreen(
                onSelectedFolder = {
                    onSelectedFolder()
                },
                onTestImageClick = {
                    onDetailImage()
                }
            )
        }
    }
}

