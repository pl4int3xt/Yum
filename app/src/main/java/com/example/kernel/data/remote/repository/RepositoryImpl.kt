package com.example.kernel.data.remote.repository

import com.example.kernel.common.Constants
import com.example.kernel.data.remote.dto.CategoryDto
import com.example.kernel.data.remote.dto.MealDetails
import com.example.kernel.data.remote.dto.MealDetailsDto
import com.example.kernel.data.remote.dto.MealDto
import com.example.kernel.domain.repository.RepositoryService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): RepositoryService {
    override suspend fun getCategories(): CategoryDto {
        return httpClient.get { url(Constants.GET_CATEGORIES_URL) }
    }

    override suspend fun getMeals(category: String): MealDto {
        return httpClient.get {
            url(Constants.GET_MEALS_URL)
            parameter(key = "c" , value = category)
        }
    }

    override suspend fun getMealDetails(meal: String): MealDetailsDto {
        return httpClient.get {
            url(Constants.GET_MEAL_DETAILS_URL)
            parameter(key = "s" , value = meal)
        }
    }

    override suspend fun searchMeal(searchQuery: String): MealDto {
        return httpClient.get{
            url(Constants.GET_MEAL_DETAILS_URL)
            parameter(key = "f" , value = searchQuery)
        }
    }
}