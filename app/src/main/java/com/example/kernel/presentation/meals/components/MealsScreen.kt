package com.example.kernel.presentation.meals.components

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
import androidx.navigation.NavHostController
import com.example.kernel.presentation.home.components.CategoryCard
import com.example.kernel.presentation.meals.MealsScreenEvents
import com.example.kernel.presentation.meals.MealsScreenViewModel
import com.example.kernel.presentation.screen.Screens
import com.example.kernel.presentation.shared.MainTopAppBar
import com.example.kernel.presentation.uievents.UiEvent
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MealsScreen(
    navHostController: NavHostController,
    onPopBackStack: () -> Unit,
    viewModel: MealsScreenViewModel = hiltViewModel()
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
            viewModel.getMeals(viewModel.category)
        }
    )

    PullRefreshIndicator(
        refreshing = state.isLoading,
        state = pullRefreshState,
        contentColor = MaterialTheme.colorScheme.primary
    )

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Meals",
                navigationIcon = Icons.Default.ArrowBackIos,
                onClickNavigation = { viewModel.onEvent(MealsScreenEvents.OnBackClicked) }) {
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
                        modifier = Modifier.height(50.dp)
                    ) {
                        Text(text = "")
                    }
                }
                items(state.meals.size){ i ->
                    SingleMeal(
                        name = state.meals[i].name,
                        image = state.meals[i].thumb,
                        onclick = {
                            navHostController.navigate(
                                Screens.DescriptionScreen.route + "/${state.meals[i].name}")
                        }
                    )
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