package com.example.kernel.presentation.screen

sealed class Screens (val route: String){
    object WelcomeScreen: Screens("welcome_screen")
    object HomeScreen: Screens("home_screen")
    object MealsScreen: Screens("meals_screen")
    object DescriptionScreen: Screens("description_screen")
}