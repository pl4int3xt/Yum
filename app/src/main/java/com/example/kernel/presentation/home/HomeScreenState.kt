package com.example.kernel.presentation.home

import com.example.kernel.domain.model.CategoryModel

data class HomeScreenState(
    val isLoading: Boolean = false,
    val categories: List<CategoryModel> = emptyList(),
    val error: String = ""
)