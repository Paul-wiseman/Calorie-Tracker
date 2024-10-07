package com.plcoding.onboarding_presentation.screen.nutrient_goal

import android.provider.Contacts.Intents.UI
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.core.R
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.util.UiEvent
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.SelectableButton
import com.plcoding.onboarding_presentation.components.UnitTextField

@Composable
fun NutrientGoalScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    nutrientGoalViewModel: NutrientGoalViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        nutrientGoalViewModel.uiEvent.collect {
            when (it) {
                is UiEvent.Navigate -> onNavigate(it)
                is UiEvent.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = it.message.uiTextToString(context)
                )
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = nutrientGoalViewModel.state.carbsRatio,
                onValueChange = {
                    nutrientGoalViewModel.onNutrientEvent(
                        NutrientGoalEvent.OnCarbRatioEnter(
                            ratio =it
                        )
                    )
                },
                unit = stringResource(R.string.percent_carbs)
            )
            Spacer(modifier = Modifier.height(20.dp))
            UnitTextField(
                value = nutrientGoalViewModel.state.proteinRatio,
                onValueChange = {
                    nutrientGoalViewModel.onNutrientEvent(
                        NutrientGoalEvent.OnProteinRatioEnter(
                            ratio =it
                        )
                    )
                },
                unit = stringResource(R.string.percent_proteins)
            )
            Spacer(modifier = Modifier.height(20.dp))
            UnitTextField(
                value = nutrientGoalViewModel.state.fatRatio,
                onValueChange = {
                    nutrientGoalViewModel.onNutrientEvent(
                        NutrientGoalEvent.onFatRatioEnter(
                            ratio =it
                        )
                    )
                },
                unit = stringResource(R.string.percent_fats)
            )
        }

        ActionButton(
            text = stringResource(R.string.next),
            onClick = { nutrientGoalViewModel.onNutrientEvent(NutrientGoalEvent.OnNextClicked) },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}