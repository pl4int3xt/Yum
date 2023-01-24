package com.example.kernel.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CategoryCard(
    name: String,
    image: String,
    onclick: () -> Unit
) {
    Box(
        modifier = Modifier.clickable { onclick() }
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ){
        AsyncImage(model = image, contentDescription = "category image")
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.background(color = Color.White)
        )
    }
}