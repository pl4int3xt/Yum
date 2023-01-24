package com.example.kernel.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    title: String,
    navigationIcon: ImageVector,
    actions: ImageVector,
    onClickNavigation: () -> Unit,
    onClickAction: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = CircleShape
                    )
                ,
                onClick = { onClickNavigation() }) {
                Icon(
                    tint = Color.White,
                    imageVector = navigationIcon, contentDescription = "")
            }
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = CircleShape
                    ),
                onClick = { onClickAction() }) {
                Icon(
                    tint = Color.White,
                    imageVector = actions, contentDescription = "")
            }
        },
        title = {
            Text(text = title)
        }
    )
}