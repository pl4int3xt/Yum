package com.example.kernel.presentation.home.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kernel.R
import com.example.kernel.presentation.home.HomeScreenEvents
import com.example.kernel.presentation.home.HomeScreenViewModel
import com.example.kernel.presentation.meals.components.SingleMeal
import com.example.kernel.presentation.screen.Screens
import com.example.kernel.presentation.uievents.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val areasState = viewModel.areasState.value
    val mealState = viewModel.mealState.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true, context){
        viewModel.uiEvent.collect {event ->
            when(event){
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Unit
            }
        }
    }

    Scaffold() {
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
            Box {
                if (viewModel.showDialog){
                    Dialog(
                        onDismissRequest = { viewModel.showDialog = false }
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if(isSystemInDarkTheme()) Color.Black else Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                                .padding(20.dp)
                            ,
                            onClick = { /*TODO*/ }) {
                            Box() {
                                if (areasState.isLoading){
                                    val lottieCompositionSpec by rememberLottieComposition(
                                        spec = LottieCompositionSpec.RawRes(R.raw.loading)
                                    )
                                    LottieAnimation(
                                        composition = lottieCompositionSpec,
                                        iterations = Int.MAX_VALUE,
                                        alignment = Alignment.Center
                                    )
                                }
                                LazyColumn(
                                    modifier = Modifier.padding(vertical = 10.dp)
                                ){
                                    items(areasState.areas){
                                        Text(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 35.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    viewModel.showDialog = false
                                                    viewModel.onEvent(
                                                        HomeScreenEvents.OnFilterTypeClicked(
                                                            it.area
                                                        )
                                                    )
                                                }
                                            ,
                                            text = it.area
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Column() {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Yum",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            modifier = Modifier.padding(10.dp),
                            onClick = {
                                viewModel.onEvent(HomeScreenEvents.OnFilterClicked)
                                viewModel.showDialog = !viewModel.showDialog },
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp
                            )
                        ) {
                            Icon(
                                modifier = Modifier.padding(10.dp),
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "filter list"
                            )
                        }
                    }
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    ) {
                        items(state.categories.size) { i ->
                            CategoryCard(
                                name = state.categories[i].name,
                                image = state.categories[i].thumb,
                                onclick = {
                                    viewModel.onEvent(HomeScreenEvents.OnCategoryClicked(state.categories[i].name))
                                }
                            )
                        }
                    }
                    Box {
                        if (mealState.isLoading) {
                            val lottieCompositionSpec by rememberLottieComposition(
                                spec = LottieCompositionSpec.RawRes(R.raw.loading)
                            )
                            LottieAnimation(
                                composition = lottieCompositionSpec,
                                iterations = Int.MAX_VALUE,
                                alignment = Alignment.Center
                            )
                        } else {
                            LazyColumn() {
                                items(mealState.meals.size) { i ->
                                    SingleMeal(
                                        name = mealState.meals[i].name,
                                        image = mealState.meals[i].thumb,
                                        onclick = {
                                            navHostController.navigate(
                                                Screens.DescriptionScreen.route + "/${mealState.meals[i].name}"
                                            )
                                        }
                                    )
                                }
                                item {
                                    Spacer(modifier = Modifier.height(100.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

