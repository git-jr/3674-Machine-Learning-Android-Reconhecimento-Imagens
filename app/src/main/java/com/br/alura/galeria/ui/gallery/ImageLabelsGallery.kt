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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.br.alura.galeria.R
import com.br.alura.galeria.data.ImageWithLabels
import com.br.alura.galeria.extensions.cleanBrackets
import com.br.alura.galeria.ui.components.ExpandedImage

@Composable
fun ImageLabelsGallery(
    imageUris: List<ImageWithLabels>,
    onBackClick: () -> Unit = {},
) {
    var selectedImageUrl by remember { mutableStateOf<ImageWithLabels?>(null) }
    var hasSelectedImage by remember { mutableStateOf(false) }

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
                        .clickable { onBackClick() }
                        .aspectRatio(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }


            items(imageUris, key = { uri -> uri.hashCode() }) { image ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image.uri)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.square_loading),
                    error = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = image.labels.toString().cleanBrackets(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .height(100.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Transparent
                        )
                        .clickable {
                            selectedImageUrl = image
                            hasSelectedImage = true
                        }
                )
            }
        }
    }
    ExpandedImage(
        isExpanded = hasSelectedImage,
        imageUri = selectedImageUrl?.uri,
        imageDescription = selectedImageUrl?.labels.toString().cleanBrackets(),
        onClose = {
            hasSelectedImage = false
        }
    )
}