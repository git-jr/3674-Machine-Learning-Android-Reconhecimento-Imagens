package com.br.alura.galeria.ui.homeScreen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.br.alura.galeria.ui.components.LoadScreen
import com.br.alura.galeria.ui.components.SlidingRectangle
import com.br.alura.galeria.ui.gallery.GroupGalleryScreen
import com.br.alura.galeria.ui.gallery.ImageGallery
import com.br.alura.galeria.ui.splashScreen.Route
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    onChangeFolder: () -> Unit = {},
) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val state by viewModel.uiState.collectAsState()

    if (state.imageUris.isEmpty()) {
        LoadScreen()
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Crossfade(
                    targetState = state.currentScreen,
                    label = "transition screen",
                ) { targetScreen ->
                    when (targetScreen) {
                        Route.LOADING -> LoadScreen(showCircleProgress = true)
                        Route.GALLERY -> {
                            ImageGallery(
                                onChangeFolder = { onChangeFolder() },
                                onHideSlideButton = {
                                    viewModel.toggleSwitchButtonVisibility(it)
                                }
                            )
                        }

                        Route.IA -> {
                            GroupGalleryScreen(
                                onChangeFolder = { onChangeFolder() },
                                onShowSlideButton = {
                                    viewModel.toggleSwitchButtonVisibility(it)
                                }
                            )
                        }
                    }
                }
            }

            Crossfade(
                targetState = state.showSwitchButton,
                label = "transition button",
            ) { showButton ->
                if (showButton) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        val scope = rememberCoroutineScope()
                        SlidingRectangle(
                            isGalleryAligned = state.defaultAlignedGallery,
                            onChanged = { isGalleryAligned ->
                                scope.launch {
                                    viewModel.toggleScreen(isGalleryAligned)
                                }
                            })
                        Spacer(modifier = Modifier.size(32.dp))
                    }
                }
            }
        }
    }

    if (state.goToSelectionFolder) {
        onChangeFolder()
    }
}
