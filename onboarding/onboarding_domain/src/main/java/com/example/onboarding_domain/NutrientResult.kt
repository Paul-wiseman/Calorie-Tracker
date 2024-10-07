package com.example.onboarding_domain

import com.plcoding.core.util.UiText

sealed class NutrientResult {
    data class Success(
        val carbRatio: Float,
        val proteinRatio: Float,
        val fatRatio: Float
    ) :
        NutrientResult()

    data class Error(val message: UiText) : NutrientResult()
}