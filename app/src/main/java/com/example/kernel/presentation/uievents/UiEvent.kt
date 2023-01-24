package com.example.kernel.presentation.uievents

sealed class UiEvent{
    data class OnNavigate(val route: String): UiEvent()
    data class ShowSnackBar(val message: String, val action: String? = null): UiEvent()
    data class ShowToast(val message: String): UiEvent()
    object PopBackStack: UiEvent()
}