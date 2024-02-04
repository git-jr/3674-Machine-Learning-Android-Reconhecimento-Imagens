package com.br.alura.galeria.navigation

sealed class Destinations(val route: String) {
    data object SplashScreen : Destinations("splash_screen")
    data object HomeGraph : Destinations("home_graph")
    data object HomeScreen : Destinations("home_screen")
    data object FolderGraph : Destinations("selection_folder_graph")
    data object SelectionFolder : Destinations("selection_folder")
    data object ImageDetail: Destinations("image_detail")
}