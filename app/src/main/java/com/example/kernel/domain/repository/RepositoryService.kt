package com.example.kernel.domain.repository

import com.example.kernel.data.remote.dto.AreaDto
import com.example.kernel.data.remote.dto.CategoryDto
import com.example.kernel.data.remote.dto.MealDetails
import com.example.kernel.data.remote.dto.MealDetailsDto
import com.example.kernel.data.remote.dto.MealDto

interface RepositoryService {
    suspend fun getCategories(): CategoryDto
    suspend fun getMeals(category: String): MealDto
    suspend fun getMealDetails(meal: String): MealDetailsDto
    suspend fun searchMeal(searchQuery: String): MealDto
    suspend fun getAreas(area: String): AreaDto
    suspend fun getMealsByArea(area: String): MealDto
}