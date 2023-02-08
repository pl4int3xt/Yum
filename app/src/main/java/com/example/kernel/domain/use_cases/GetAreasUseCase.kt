package com.example.kernel.domain.use_cases

import com.example.kernel.common.Resource
import com.example.kernel.data.remote.dto.toAreaModel
import com.example.kernel.data.remote.dto.toCategoryModel
import com.example.kernel.domain.model.AreaModel
import com.example.kernel.domain.model.CategoryModel
import com.example.kernel.domain.repository.RepositoryService
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAreasUseCase @Inject constructor(
    private val repositoryService: RepositoryService
) {
    operator fun invoke(area: String): Flow<Resource<List<AreaModel>>> = flow {
        try {
            emit(Resource.Loading())
            val areas = repositoryService.getAreas(area).meals.map { it.toAreaModel() }
            emit(Resource.Success(areas))
        } catch (e: RedirectResponseException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: ClientRequestException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: ServerResponseException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: IOException){
            emit(Resource.Error("Can't reach server, check your internet connection"))
        }catch (e: TimeoutCancellationException){
            emit(Resource.Error("Timeout, check your internet connection"))
        }
    }
}