package com.br.alura.galeria.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.br.alura.galeria.navigation.Destinations.*
import com.br.alura.galeria.navigation.graphs.folderGraph
import com.br.alura.galeria.navigation.graphs.homeGraph
import com.br.alura.galeria.navigation.graphs.imageDetailGraph
import com.br.alura.galeria.navigation.graphs.splashGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreen.route,
        modifier = modifier
    ) {
        splashGraph(
            onNavigateHome = {
                navController.navigate(HomeGraph.route)
            },
            onNavigateSelectFolder = {
                navController.navigate(FolderGraph.route)
            }
        )

        homeGraph(
            onChangeFolder = {
                navController.navigate(FolderGraph.route)
            }
        )

        folderGraph(
            onSelectedFolder = {
                navController.navigate(SplashScreen.route)
            },
            onDetailImage = {
                navController.navigate(ImageDetail.route)
            }
        )

        imageDetailGraph()
    }

}