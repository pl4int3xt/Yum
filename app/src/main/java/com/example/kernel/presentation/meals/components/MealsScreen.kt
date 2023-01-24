package com.example.kernel.presentation.meals.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kernel.presentation.home.components.CategoryCard
import com.example.kernel.presentation.screen.Screens
import com.example.kernel.presentation.shared.MainTopAppBar

@Composable
fun MealsScreen() {

    val state = viewModel.state.value

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
                title = "Home",
                navigationIcon = Icons.Default.Home,
                actions = Icons.Default.Logout,
                onClickNavigation = { /*TODO*/ }) {
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

                    }
                }
                item {
                    Text(
                        text = "Find Best Recipe\nfor cooking",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
                item {
                    Text(
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
            if (state.error.isNotBlank()){
                Text(
                    text = state.error,
                    color = androidx.compose.material.MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
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