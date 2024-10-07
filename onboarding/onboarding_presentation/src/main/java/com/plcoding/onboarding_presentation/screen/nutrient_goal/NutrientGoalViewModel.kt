package com.plcoding.onboarding_presentation.screen.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboarding_domain.NutrientResult
import com.example.onboarding_domain.use_case.ValidateNutrientGoal
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.core.domain.use_case.FilterOutDigits
import com.plcoding.core.navigation.Route
import com.plcoding.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrientGoal: ValidateNutrientGoal
) : ViewModel() {

    var state by mutableStateOf(NutrientGoalState())

    private var _uiState = Channel<UiEvent>()
    val uiEvent = _uiState.receiveAsFlow()

    fun onNutrientEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                state = state.copy(carbsRatio = filterOutDigits(event.ratio))
            }

            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(proteinRatio = filterOutDigits(event.ratio))
            }

            is NutrientGoalEvent.onFatRatioEnter -> {
                state = state.copy(fatRatio = filterOutDigits(event.ratio))
            }

            is NutrientGoalEvent.OnNextClicked -> {
                val nutrientGoal = validateNutrientGoal(
                    carbRatio = state.carbsRatio,
                    proteinRatio = state.proteinRatio,
                    fatRatio = state.fatRatio
                )

                when (nutrientGoal) {
                    is NutrientResult.Error -> {
                        viewModelScope.launch {
                            _uiState.send(UiEvent.ShowSnackBar(nutrientGoal.message))
                        }
                    }

                    is NutrientResult.Success -> {
                        preferences.saveCarbRatio(nutrientGoal.carbRatio)
                        preferences.saveProteinRatio(nutrientGoal.proteinRatio)
                        preferences.saveFatRatio(nutrientGoal.fatRatio)
                        viewModelScope.launch {
                            _uiState.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                }

            }
        }
    }
}