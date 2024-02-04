package com.br.alura.galeria.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.alura.galeria.ui.theme.gradientPurple


@Composable
fun SlidingRectangle(
    isGalleryAligned: Boolean,
    onChanged: (Boolean) -> Unit
) {
    val position by animateDpAsState(
        targetValue = if (isGalleryAligned) 0.dp else 120.dp,
        label = "position"
    )
    val size by animateDpAsState(
        targetValue = if (isGalleryAligned) 125.dp else 80.dp,
        label = "size"
    )
    val round = 10.dp

    Box(
        modifier = Modifier
            .shadow(8.dp)
            .size(width = 200.dp, height = 45.dp)
            .clip(shape = RoundedCornerShape(round))
            .background(Color.White)
            .clickable {
                onChanged(!isGalleryAligned)
            },
        contentAlignment = Alignment.CenterStart
    ) {

        Box(
            modifier = Modifier
                .size(width = size, height = 45.dp)
                .align(Alignment.CenterStart)
                .offset(x = position)
                .clip(shape = RoundedCornerShape(round))
                .background(
                    brush = horizontalGradient(gradientPurple)
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Galeria", fontSize = 20.sp, color =
                if (isGalleryAligned) Color.White else Color.DarkGray
            )
            Text(
                text = "IA", fontSize = 20.sp, color =
                if (isGalleryAligned) Color.DarkGray else Color.White
            )
        }
    }
}
