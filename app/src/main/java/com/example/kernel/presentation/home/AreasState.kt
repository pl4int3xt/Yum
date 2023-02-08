package com.example.kernel.presentation.home

import com.example.kernel.domain.model.AreaModel

data class AreasState(
    val isLoading: Boolean = false,
    val areas: List<AreaModel> = emptyList(),
    val error: String = ""
)