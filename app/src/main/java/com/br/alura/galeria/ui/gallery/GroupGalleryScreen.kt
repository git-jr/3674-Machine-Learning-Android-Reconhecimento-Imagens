package com.br.alura.galeria.ui.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.br.alura.galeria.R
import com.br.alura.galeria.data.ImageWithLabels

@Composable
fun GroupGalleryScreen(
    onChangeFolder: () -> Unit,
    onShowSlideButton: (Boolean) -> Unit = {}
) {
    val viewModel = hiltViewModel<GroupGalleryViewModel>()
    val state by viewModel.uiState.collectAsState()

    val selectedGroup = remember { mutableStateListOf<ImageWithLabels>() }
    val groupsList = state.groups

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(groupsList,
                    key = { uri -> uri.hashCode() }
                ) { group ->
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onChangeFolder() },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(group.images.first().uri)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(id = R.drawable.square_loading),
                            error = painterResource(id = R.drawable.placeholder_image),
                            contentDescription = "Imagem da galeria",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(15))
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(15)
                                )
                                .clickable {
                                    onShowSlideButton(false)
                                    selectedGroup.addAll(group.images)
                                }
                        )

                        Text(
                            text = "${group.name} (${group.images.size})",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }


    if (selectedGroup.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            ImageLabelsGallery(
                imageUris = selectedGroup.toList(),
                onBackClick = {
                    selectedGroup.clear()
                    onShowSlideButton(true)
                }
            )
        }
    }
}