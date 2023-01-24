package com.example.kernel.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.*
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    name: String,
    image: String,
    description: String,
    onclick: () -> Unit
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp
            )
            .height(200.dp)
            .clickable { onclick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {
            Text(text = name)
            Text(text = description)
        }
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(model = image, contentDescription = "category image")
    }
}