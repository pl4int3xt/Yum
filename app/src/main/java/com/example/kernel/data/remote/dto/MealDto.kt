package com.example.kernel.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    val meals: List<Meal>
)