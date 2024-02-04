package com.br.alura.galeria.ui.selectionFolderScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.br.alura.galeria.R
import com.br.alura.galeria.extensions.getRootStorage
import com.br.alura.galeria.extensions.persistUriPermission
import com.br.alura.galeria.ui.components.LoadScreen
import com.br.alura.galeria.ui.theme.gradientPurple

@Composable
fun SelectionFolderScreen(
    onSelectedFolder: () -> Unit = {},
    onTestImageClick: () -> Unit = {},
) {
    val viewModel = hiltViewModel<SelectionFolderViewModel>()
    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) {
        it?.let { treeUri ->
            context.persistUriPermission(treeUri)
            viewModel.savePath(treeUri.toString())
        } ?: run {
            viewModel.setLoading(false)
        }
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (state.loading) {
            LoadScreen(showCircleProgress = true)
        } else {
            SelectScreen(
                onTestImageClick = onTestImageClick
            ) {
                viewModel.setLoading(true)
                requestPermissionLauncher.launch(context.getRootStorage())
            }
        }
    }

    LaunchedEffect(state.savedPath) {
        if (state.savedPath) {
            onSelectedFolder()
        }
    }
}

@Composable
private fun SelectScreen(
    onTestImageClick: () -> Unit = {},
    onSelectClick: () -> Unit,
) {
    Spacer(modifier = Modifier.size(72.dp))
    Image(
        painter = painterResource(id = R.drawable.logo_galeriia_text),
        contentDescription = "app logo",
        modifier = Modifier
            .width(300.dp)
            .height(150.dp),
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.size(32.dp))

    Column(
        Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .border(
                2.dp,
                brush = Brush.sweepGradient(gradientPurple),
                shape = MaterialTheme.shapes.extraLarge
            )
            .size(150.dp)
            .clickable {
                onSelectClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painterResource(id = R.drawable.ic_folder),
            contentDescription = "Selecionar pasta",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(40.dp)
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            horizontalGradient(gradientPurple),
                            blendMode = BlendMode.SrcAtop
                        )
                    }
                }
        )
    }

    Spacer(modifier = Modifier.size(24.dp))

    Text(
        text = "Selecione uma pasta para continuar",
        color = MaterialTheme.colorScheme.primary,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 50.dp)
    )

    Spacer(modifier = Modifier.size(48.dp))

    Row(
        Modifier
            .border(
                2.dp,
                brush = Brush.sweepGradient(gradientPurple),
                shape = MaterialTheme.shapes.large
            )
            .clip(MaterialTheme.shapes.large)
            .clickable {
                onTestImageClick()
            }
            .padding(16.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painterResource(id = R.drawable.ic_photo),
            contentDescription = "Seleciona apenas uma imagem",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            horizontalGradient(gradientPurple),
                            blendMode = BlendMode.SrcAtop
                        )
                    }
                }
        )

        Text(
            text = "Testar uma imagem",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

