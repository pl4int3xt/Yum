package com.example.kernel.presentation.home

sealed class HomeScreenEvents {
    object OnExitClicked: HomeScreenEvents()
    data class OnSearchTextChanged(val searchQuery: String): HomeScreenEvents()
    object OnSearchClicked: HomeScreenEvents()
    data class OnCategoryClicked(val category: String): HomeScreenEvents()
}