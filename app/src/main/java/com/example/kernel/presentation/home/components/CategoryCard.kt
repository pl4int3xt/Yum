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

@Composable
fun CategoryCard(
    name: String,
    image: String,
    description: String,
    onclick: () -> Unit
) {
    CategoryCard(
        name = name,
        image = image,
        description = description,
        onclick = onclick
    )
}