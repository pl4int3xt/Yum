package com.example.kernel.data.remote.repository

import com.example.kernel.common.Constants
import com.example.kernel.data.remote.dto.CategoryDto
import com.example.kernel.data.remote.dto.MealDetails
import com.example.kernel.data.remote.dto.MealDetailsDto
import com.example.kernel.data.remote.dto.MealDto
import com.example.kernel.domain.repository.RepositoryService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): RepositoryService {
    override suspend fun getCategories(): CategoryDto {
        return httpClient.get { url(Constants.GET_CATEGORIES_URL) }
    }

    override suspend fun getMeals(category: String): MealDto {
        TODO("Not yet implemented")
    }

    override suspend fun getMealDetails(meal: String): MealDetailsDto {
        TODO("Not yet implemented")
    }

    override suspend fun searchMeal(searchWord: String): MealDetails {
        TODO("Not yet implemented")
    }
}