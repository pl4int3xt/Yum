package com.example.kernel.presentation.meals.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun SingleMeal(
    name: String,
    image: String,
    onclick: () -> Unit
) {
    Divider()
    Column(
        modifier = Modifier
            .clickable {
                onclick()
            }
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = image,
            contentDescription = "image",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(300.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}