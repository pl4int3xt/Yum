package com.example.kernel.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.kernel.presentation.description.components.DescriptionScreen
import com.example.kernel.presentation.home.components.HomeScreen
import com.example.kernel.presentation.meals.components.MealsScreen
import com.example.kernel.presentation.screen.Screens
import com.example.kernel.presentation.welcome.components.WelcomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(route = Screens.WelcomeScreen.route){
            WelcomeScreen()
        }
        composable(route = Screens.HomeScreen.route){
            HomeScreen(
                navHostController = navHostController
            )
        }
        composable(route = Screens.MealsScreen.route + "/{category}"){
            MealsScreen(
                navHostController = navHostController,
                onPopBackStack = { navHostController.popBackStack() }
            )
        }
        composable(route = Screens.DescriptionScreen.route + "/{meal}"){
            DescriptionScreen(

            )
        }
    }
}