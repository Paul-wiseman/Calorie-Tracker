package com.example.onboarding_domain.use_case

import com.example.onboarding_domain.NutrientResult
import com.plcoding.core.R
import com.plcoding.core.util.UiText
import javax.inject.Inject

class ValidateNutrientGoal @Inject constructor() {
    operator fun invoke(
        carbRatio: String,
        proteinRatio: String,
        fatRatio: String
    ): NutrientResult {
        val carbRatioInt = carbRatio.toIntOrNull()
        val proteinRatioInt = proteinRatio.toIntOrNull()
        val fatRatioInt = fatRatio.toIntOrNull()

        if (carbRatioInt == null || proteinRatioInt == null || fatRatioInt == null) {
            return NutrientResult.Error(UiText.StringResource(R.string.error_not_100_percent))
        }

        if (carbRatioInt + proteinRatioInt + fatRatioInt != 100) {
            return NutrientResult.Error(UiText.StringResource(R.string.error_not_100_percent))
        }

        return NutrientResult.Success(
            carbRatio = carbRatioInt / 100f,
            proteinRatio = proteinRatioInt / 100f,
            fatRatio = fatRatioInt / 100f
        )
    }

}