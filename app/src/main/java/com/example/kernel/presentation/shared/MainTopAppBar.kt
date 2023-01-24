package com.example.kernel.presentation.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    title: String,
    navigationIcon: ImageVector,
    actions: ImageVector? = null,
    onClickNavigation: () -> Unit,
    onClickAction: () -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = Modifier.padding(5.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.White
        ),
        navigationIcon = {
            Button(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                contentPadding = PaddingValues(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 5.dp,
                    focusedElevation = 5.dp,
                    hoveredElevation = 5.dp,
                ),
                onClick = { onClickNavigation() }
            ){
                Icon(
                    tint = Color.Black,
                    imageVector = navigationIcon, contentDescription = "")
            }
        },
        actions = {
            if (actions != null) {
                Button(
                    modifier = Modifier.size(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 5.dp,
                        focusedElevation = 5.dp,
                        hoveredElevation = 5.dp,
                    ),
                    shape = CircleShape
                    ,
                    onClick = { onClickAction() }) {
                        Icon(
                            tint = Color.Black,
                            imageVector = actions, contentDescription = "")
                }
            }
        },
        title = {
            Text(text = title)
        }
    )
}