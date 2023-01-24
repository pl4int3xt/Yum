package com.example.kernel.presentation.meals

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kernel.common.Resource
import com.example.kernel.domain.use_cases.GetMealsUseCase
import com.example.kernel.presentation.home.HomeScreenEvents
import com.example.kernel.presentation.home.HomeScreenState
import com.example.kernel.presentation.uievents.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsScreenViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var category: String? by mutableStateOf("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    init {
        category = savedStateHandle.get<String>("category")
        getMeals(category)
    }

    fun getMeals(category: String?){
        getMealsUseCase(category?:"").onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(error = result.message?:"unexpected error occurred")
                }
                is Resource.Success -> {
                    _state.value = HomeScreenState(categories = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(homeScreenEvents: HomeScreenEvents){

    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}