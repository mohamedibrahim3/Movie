package com.mada.movieapp.navigation.details

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mada.movieapp.domain.data.Details

@Composable
fun ImageRow(details: Details) {
    if (details.images.isNotEmpty()) {
        LazyRow {
            items(details.images.size) {
                AsyncImage(
                    model = details.images[it], contentDescription = "",
                    Modifier
                        .padding(6.dp)
                        .height(70.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}