package com.br.alura.galeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.br.alura.galeria.navigation.AppNavHost
import com.br.alura.galeria.ui.theme.GaleriIATheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaleriIATheme {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                )
            }
        }
    }
}

