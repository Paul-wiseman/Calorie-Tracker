package com.plcoding.tracker_domain.model

import com.plcoding.core.util.UiState

data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100g: Int,
    val carbsPer100g: Int,
    val proteinPer100g: Int,
    val fatPer100g: Int
) : UiState
