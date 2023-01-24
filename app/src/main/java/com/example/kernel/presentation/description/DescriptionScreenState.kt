package com.example.kernel.presentation.description

import com.example.kernel.domain.model.MealDetailsModel

data class DescriptionScreenState(
    val isLoading: Boolean = false,
    val mealDetails: MealDetailsModel? = null,
    val error: String = ""
)