package com.example.kernel.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CategoryCard(
    name: String,
    image: String,
    onclick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onclick() }
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
            pressedElevation = 0.dp
        )
    ){
        AsyncImage(
            alignment = Alignment.Center,
            modifier = Modifier.fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(10.dp)
            ,
            model = image,
            contentDescription = "category image")
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxHeight(1f)
                .fillMaxWidth()
            ,
            text = name,
        )
    }
}