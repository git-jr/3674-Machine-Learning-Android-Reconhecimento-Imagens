package com.br.alura.galeria.ui.imageDetail

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.br.alura.galeria.ui.theme.gradientPurple

@Composable
fun ImageDetailScreen(
    defaultImage: Any,
    description: String,
    onImageChange: (Uri) -> Unit = {}
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            it?.let { uri ->
                onImageChange(uri)
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .clip(shape = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50))
                .background(Brush.horizontalGradient(gradientPurple)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "GALERI-IA",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 32.dp)
                .clip(shape = RoundedCornerShape(bottomEndPercent = 10, bottomStartPercent = 10))
                .border(
                    width = 2.dp,
                    brush = Brush.sweepGradient(gradientPurple),
                    shape = RoundedCornerShape(bottomEndPercent = 10, bottomStartPercent = 10)
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                defaultImage,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                contentDescription = description
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush =
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
                    .padding(16.dp),
            ) {

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tags:",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.background
                )
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}