package com.example.kernel.presentation.description.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kernel.presentation.description.DescriptionScreenEvents
import com.example.kernel.presentation.description.DescriptionScreenViewModel
import com.example.kernel.presentation.shared.MainTopAppBar
import com.example.kernel.presentation.uievents.UiEvent
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DescriptionScreen(
    onPopBackStack: () -> Unit,
    viewModel: DescriptionScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value

    LaunchedEffect(context){
        viewModel.uiEvent.onEach { event ->
            when(event){
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiEvent.PopBackStack -> { onPopBackStack() }
                else -> Unit
            }
        }
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading, onRefresh = {
            viewModel.getMealDetails(viewModel.meal)
        }
    )

    PullRefreshIndicator(
        refreshing = state.isLoading,
        state = pullRefreshState,
        contentColor = MaterialTheme.colorScheme.primary
    )

    Scaffold(
        topBar = {
            state.mealDetails?.let {
                MainTopAppBar(
                    title = it.name,
                    navigationIcon = Icons.Default.ArrowBackIos,
                    onClickNavigation = { viewModel.onEvent(DescriptionScreenEvents.OnExitClicked) }) {
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pullRefresh(pullRefreshState)
                .fillMaxHeight(1f)
        ) {
            LazyColumn(){
                item {
                    Column(
                        modifier = Modifier.height(200.dp)
                    ) {
                        Text(text = "")
                    }
                }
                item {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                        ,
                        model = state.mealDetails?.image, contentDescription = "image")
                }
                item {
                    Column() {
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
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = state.isLoading,
                contentColor = MaterialTheme.colorScheme.primary,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}