package com.example.kernel.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kernel.common.Resource
import com.example.kernel.domain.use_cases.GetAreasUseCase
import com.example.kernel.domain.use_cases.GetCategoriesUseCase
import com.example.kernel.domain.use_cases.GetMealsByAreaUseCase
import com.example.kernel.domain.use_cases.GetMealsUseCase
import com.example.kernel.domain.use_cases.SearchMealUseCase
import com.example.kernel.presentation.uievents.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMealsUseCase: GetMealsUseCase,
    private val searchMealUseCase: SearchMealUseCase,
    private val getAreasUseCase: GetAreasUseCase,
    private val getMealsByAreaUseCase: GetMealsByAreaUseCase
):ViewModel() {

    var showDialog by mutableStateOf(false)
    var area by mutableStateOf("list")
    var category by mutableStateOf("beef")
    var searchQuery by mutableStateOf("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    private val _mealState = mutableStateOf(MealsState())
    val mealState: State<MealsState> = _mealState

    private val _areaState = mutableStateOf(AreasState())
    val areasState: State<AreasState> = _areaState
    init {
        getCategories()
        getMeals(category)
    }
    private fun getCategories(){
        getCategoriesUseCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(error = result.message?:"unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _state.value = HomeScreenState(categories = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getMealsByArea(area: String){
        getMealsByAreaUseCase(area).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _mealState.value = MealsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealState.value = MealsState(error = result.message?:"unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _mealState.value = MealsState(meals = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getMeals(category: String?){
        getMealsUseCase(category?:"").onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _mealState.value = MealsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealState.value = MealsState(error = result.message?:"unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _mealState.value = MealsState(meals = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun searchMeal(searchQuery: String){
        searchMealUseCase(searchQuery).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _mealState.value = MealsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealState.value = MealsState(error = result.message?:"unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _mealState.value = MealsState(meals = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAreas(){
        getAreasUseCase(area).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _areaState.value = AreasState(isLoading = true)
                }
                is Resource.Error -> {
                    _areaState.value = AreasState(error = result.message?:"unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _areaState.value = AreasState(areas = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
    fun onEvent(homeScreenEvents: HomeScreenEvents){
        when(homeScreenEvents){
            is HomeScreenEvents.OnSearchTextChanged -> {
                searchQuery = homeScreenEvents.searchQuery
            }
            is HomeScreenEvents.OnExitClicked -> {

            }
            is HomeScreenEvents.OnSearchClicked -> {
                searchMeal(searchQuery)
            }
            is HomeScreenEvents.OnCategoryClicked -> {
                category = homeScreenEvents.category
                getMeals(category)
            }
            is HomeScreenEvents.OnFilterClicked -> {
                getAreas()
            }
            is HomeScreenEvents.OnFilterTypeClicked -> {
                area = homeScreenEvents.filterType
                getMealsByArea(area)
            }
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}