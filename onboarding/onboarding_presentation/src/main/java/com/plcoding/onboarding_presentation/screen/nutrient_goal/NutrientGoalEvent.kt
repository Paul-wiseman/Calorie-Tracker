package com.plcoding.onboarding_presentation.screen.nutrient_goal

sealed class NutrientGoalEvent {
    data class OnCarbRatioEnter(val ratio: String) : NutrientGoalEvent()
    data class OnProteinRatioEnter(val ratio: String) : NutrientGoalEvent()
    data class onFatRatioEnter(val ratio: String) : NutrientGoalEvent()
    object OnNextClicked : NutrientGoalEvent()
}