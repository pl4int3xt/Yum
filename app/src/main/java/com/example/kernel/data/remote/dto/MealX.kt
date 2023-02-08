package com.example.kernel.data.remote.dto

import com.example.kernel.domain.model.AreaModel
import kotlinx.serialization.Serializable

@Serializable
data class MealX(
    val strArea: String
)

fun MealX.toAreaModel(): AreaModel{
    return AreaModel(
        area = strArea
    )
}