package com.mada.movieapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mada.movieapp.R

@Composable
fun BannerScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent) // Set background to transparent
    ) {
        // Rest of your code for image and button
        Image(
            painter = painterResource(id = R.drawable.miles),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = Color.White.copy(0.4f), RoundedCornerShape(20.dp))
                .border(0.5.dp, Color.White, RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
        ) {

        }
    }
}

