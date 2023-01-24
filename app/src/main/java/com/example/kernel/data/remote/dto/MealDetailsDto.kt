package com.example.kernel.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDetailsDto(
    val meals: List<MealDetails>
)