package com.example.kernel.data.remote.dto

import com.example.kernel.domain.model.MealModel
import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

fun Meal.toMealModel(): MealModel{
    return MealModel(
        id = idMeal,
        name = strMeal,
        thumb = strMealThumb
    )
}