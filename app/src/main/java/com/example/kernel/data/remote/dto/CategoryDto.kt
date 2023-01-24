package com.example.kernel.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val categories: List<Category>
)