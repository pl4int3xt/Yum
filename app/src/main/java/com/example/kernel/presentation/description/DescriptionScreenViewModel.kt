package com.example.kernel.presentation.description

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kernel.common.Resource
import com.example.kernel.domain.use_cases.GetMealDetailsUseCase
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
class DescriptionScreenViewModel @Inject constructor(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    var meal: String? by mutableStateOf("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(DescriptionScreenState())
    val state: State<DescriptionScreenState> = _state

    init {
        meal = savedStateHandle.get<String>("meal")
        getMealDetails(meal)
    }

    fun getMealDetails(meal: String?){
        getMealDetailsUseCase(meal?:"").onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = DescriptionScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = DescriptionScreenState(error = result.message?:"unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _state.value = DescriptionScreenState(mealDetails = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(descriptionScreenEvents: DescriptionScreenEvents){
        when(descriptionScreenEvents){
            DescriptionScreenEvents.OnExitClicked -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}