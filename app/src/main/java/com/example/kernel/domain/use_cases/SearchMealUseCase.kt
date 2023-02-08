package com.example.kernel.domain.use_cases

import com.example.kernel.common.Resource
import com.example.kernel.data.remote.dto.toMealModel
import com.example.kernel.domain.model.MealModel
import com.example.kernel.domain.repository.RepositoryService
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchMealUseCase @Inject constructor(
    private val repositoryService: RepositoryService
) {
    operator fun invoke(searchQuery: String): Flow<Resource<List<MealModel>>> = flow {
        try {
            emit(Resource.Loading())
            val meals = repositoryService.searchMeal(searchQuery).meals.map { it.toMealModel() }
            emit(Resource.Success(meals))
        } catch (e: RedirectResponseException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: ClientRequestException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: ServerResponseException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: IOException){
            emit(Resource.Error("Can't reach server, check your internet connection"))
        }
    }
}