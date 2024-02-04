package com.br.alura.galeria.ui.components

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.br.alura.galeria.extensions.noRippleClickable


@Composable
fun ExpandedImage(
    imageUri: String?,
    imageDescription: String,
    isExpanded: Boolean,
    onClose: () -> Unit = {}
) {
    val scale by animateFloatAsState(
        if (isExpanded) 1f else 0f, label = "image scale",
        animationSpec = tween(500)
    )

    Crossfade(
        targetState = isExpanded,
        animationSpec = tween(800),
        label = "image background"
    ) { hasImage ->
        if (hasImage) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(0.9f))
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .noRippleClickable {
                onClose()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)

        ) {
            Log.e("imageDescription", imageDescription)
            AsyncImage(
                model = imageUri,
                contentDescription = "Imagem expandida",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = imageDescription,
                Modifier.padding(16.dp)
            )
        }
    }
}