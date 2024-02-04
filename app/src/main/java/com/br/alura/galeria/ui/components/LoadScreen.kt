package com.br.alura.galeria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.br.alura.galeria.R


@Composable
fun LoadScreen(
    modifier: Modifier = Modifier,
    showCircleProgress: Boolean = false,
    message: String? = null,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = R.drawable.vertical_loading,
            modifier = Modifier
                .fillMaxSize(),
            contentDescription = "Background loading",
            contentScale = ContentScale.Crop,
            alpha = 0.5f,
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_galeriia_text),
                contentDescription = "galeri-ia text logo",
            )

            message?.let {
                Text(text = message)
                Spacer(modifier = Modifier.size(10.dp))
            }
            if (showCircleProgress) {
                CircularProgressIndicator()
            }
        }
    }
}
