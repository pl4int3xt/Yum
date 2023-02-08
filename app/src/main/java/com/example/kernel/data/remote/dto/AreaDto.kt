package com.example.kernel.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AreaDto(
    val meals: List<MealX>
)