package com.br.alura.galeria.navigation.graphs

import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.br.alura.galeria.R
import com.br.alura.galeria.navigation.Destinations
import com.br.alura.galeria.ui.imageDetail.ImageDetailScreen


fun NavGraphBuilder.imageDetailGraph() {
    composable(
        route = Destinations.ImageDetail.route
    ) {
        val context = LocalContext.current

        var description by remember {
            mutableStateOf("#descrição #da #imagem")
        }

        val imageBitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.image_test
        )

        var currentImage: Any by remember {
            mutableStateOf(imageBitmap)
        }


        ImageDetailScreen(
            defaultImage = currentImage,
            description = description,
            onImageChange = {
                currentImage = it
            }
        )
    }
}