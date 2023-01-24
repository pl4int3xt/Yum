package com.example.kernel.presentation.meals

import com.example.kernel.domain.model.MealModel

data class MealsScreenState(
    val isLoading: Boolean = false,
    val meals: List<MealModel> = emptyList(),
    val error: String = ""
)