package com.example.kernel.presentation.description.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kernel.R
import com.example.kernel.presentation.description.DescriptionScreenEvents
import com.example.kernel.presentation.description.DescriptionScreenViewModel
import com.example.kernel.presentation.shared.MainTopAppBar
import com.example.kernel.presentation.uievents.UiEvent
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionScreen(
    onPopBackStack: () -> Unit,
    viewModel: DescriptionScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }
    
    Scaffold(
        topBar = {
            state.mealDetails?.let {
                MainTopAppBar(
                    title = "",
                    navigationIcon = Icons.Default.ArrowBack,
                    onClickNavigation = { viewModel.onEvent(DescriptionScreenEvents.OnExitClicked) }) {
                }
            }
        },
    ) {
        if (state.isLoading){
            val lottieCompositionSpec by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.loading)
            )
            LottieAnimation(
                composition = lottieCompositionSpec,
                iterations = Int.MAX_VALUE,
                alignment = Alignment.Center
            )
        } else {
            Box{
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .graphicsLayer {
                                translationY = 0.4f * scrollState.value
                            }
                    ){
                        AsyncImage(
                            contentScale = ContentScale.FillBounds,
                            model = state.mealDetails?.image,
                            contentDescription = "image")
                    }
                    Column(
                        modifier = Modifier
                            .background(
                                color = if (isSystemInDarkTheme()) Color.Black else Color.White
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .background(
                                    color = if (isSystemInDarkTheme()) Color.Black else Color.White
                                )
                        ) {
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.category,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.area,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                            Text(
                                text = "Ingredients",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.ingredient1,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.ingredient10,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.ingredient11,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.ingredient12,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.ingredient13,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.ingredient14,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.ingredient15,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            Text(
                                text = "Measures",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.measure1,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.measure10,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.measure11,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.measure12,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.measure13,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            Text(
                                text = "Instructions",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            state.mealDetails?.let { it1 ->
                                Text(
                                    text = it1.instructions,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
            }
        }
    }
}