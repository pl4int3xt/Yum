package com.example.kernel.presentation.home.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.kernel.presentation.home.HomeScreenViewModel
import com.example.kernel.presentation.screen.Screens
import com.example.kernel.presentation.shared.MainTopAppBar
import com.example.kernel.presentation.uievents.UiEvent
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

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

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading, onRefresh = {
            viewModel.getCategories()
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
                scrollBehavior = scrollBehavior,
                title = "Home",
                navigationIcon = Icons.Default.Home,
                onClickNavigation = { /*TODO*/ }) {
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
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
                        modifier = Modifier.height(100.dp)
                    ) {

                    }
                }
                item {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Find Best Recipe\nfor cooking",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Categories",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                items(state.categories.size){ i ->
                    CategoryCard(
                        name = state.categories[i].name,
                        image = state.categories[i].thumb,
                        onclick = {
                            navHostController.navigate(
                                Screens.MealsScreen.route + "/${state.categories[i].name}")
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

