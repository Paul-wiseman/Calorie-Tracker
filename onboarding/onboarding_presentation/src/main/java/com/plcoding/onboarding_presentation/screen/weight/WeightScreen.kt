package com.plcoding.onboarding_presentation.screen.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.core.R
import com.plcoding.core.util.UiEvent
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.UnitTextField

@Composable
fun WeightScreen(
    scaffoldStat: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    weightViewModel: WeightViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        weightViewModel.uiEvent.collect {
            when (it) {
                is UiEvent.Navigate -> onNavigate(it)
                is UiEvent.ShowSnackBar -> scaffoldStat.snackbarHostState.showSnackbar(
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
                text = stringResource(R.string.whats_your_weight),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = weightViewModel.weight,
                onValueChange = weightViewModel::onWeightEntered,
                unit = stringResource(R.string.kg)
            )
        }

        ActionButton(
            text = stringResource(R.string.next),
            onClick = weightViewModel::onNextClicked,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}