package com.br.alura.galeria.ui.splashScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br.alura.galeria.ui.components.LoadScreen

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    LoadScreen(modifier, showCircleProgress = true)
}




