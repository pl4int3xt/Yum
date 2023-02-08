package com.example.kernel.presentation.home

import com.example.kernel.domain.model.MealModel

data class MealsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val meals: List<MealModel> = emptyList(),
)