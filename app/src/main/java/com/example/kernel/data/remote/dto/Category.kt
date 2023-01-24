package com.example.kernel.data.remote.dto

import com.example.kernel.domain.model.CategoryModel
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)

fun Category.toCategoryModel(): CategoryModel{
    return CategoryModel(
        id = idCategory,
        name = strCategory,
        description = strCategoryDescription,
        thumb = strCategoryThumb
    )
}