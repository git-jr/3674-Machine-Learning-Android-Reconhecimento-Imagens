package com.br.alura.galeria.ui.gallery

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.br.alura.galeria.R
import com.br.alura.galeria.extensions.cleanBrackets
import com.br.alura.galeria.ui.components.ExpandedImage
import com.br.alura.galeria.ui.theme.gradientPurple

@Composable
fun ImageGallery(
    onChangeFolder: () -> Unit = {},
    onHideSlideButton: (Boolean) -> Unit = {}
) {
    val viewModel = hiltViewModel<ImageGalleryViewModel>()
    val state by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .clickable { onChangeFolder() }
                        .aspectRatio(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_folder),
                        contentDescription = "Trocar pasta",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(24.dp)
                            .graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(
                                        Brush.horizontalGradient(gradientPurple),
                                        blendMode = BlendMode.SrcAtop
                                    )
                                }
                            }
                    )
                }
            }


            items(state.imageUris, key = { it.hashCode() }) { image ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image.uri)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.square_loading),
                    error = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "Imagem da galeria",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .height(100.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Transparent
                        )
                        .clickable {
                            viewModel.setSelectedImage(image)
                        }
                )
            }
        }
    }

    ExpandedImage(
        isExpanded = state.hasSelectedImage,
        imageUri = state.selectedImage?.uri.toString(),
        imageDescription = state.selectedImage?.labels?.toString()?.cleanBrackets().toString(),
        onClose = {
            viewModel.toggleSwitchButtonVisibility(false)
        }
    )

    LaunchedEffect(state.hasSelectedImage) {
        onHideSlideButton(!state.hasSelectedImage)
    }
}


