package com.mada.movieapp.navigation.banner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .border(0.5.dp, Color.White, RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
        ) {
            val offset = Offset(8.0f, 5f)
            Text(
                text = "Enjoy the world of movies",
                Modifier.padding(vertical = 25.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 34.sp,
                    shadow = Shadow(
                        color = Color(0xFF8C0000),
                        offset = offset, blurRadius = 1f
                    ),
                    fontFamily = FontFamily(Font(R.font.abyssinica_sil)),
                    textAlign = TextAlign.Center
                )
            )
            Button(
                onClick = {  navController.navigate("Home screen") },
                modifier =  Modifier
                    .padding(bottom = 55.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Set button background to transparent
                    contentColor = Color.White // Keep text color as white
                ),
                contentPadding = PaddingValues(16.dp) // Specify padding for content inside the button
            ) {
                Text(text = "Get In" ,style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.abyssinica_sil)),
                    textAlign = TextAlign.Center
                ))
            }
        }
    }
}
