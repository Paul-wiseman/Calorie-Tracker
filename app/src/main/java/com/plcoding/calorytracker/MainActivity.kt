package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.calorytracker.navigation.navigate
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.plcoding.core.navigation.Route
import com.plcoding.onboarding_presentation.screen.activity.ActivityLevelScreen
import com.plcoding.onboarding_presentation.screen.age.AgeScreen
import com.plcoding.onboarding_presentation.screen.gender.GenderScreen
import com.plcoding.onboarding_presentation.screen.goal.GoalScreen
import com.plcoding.onboarding_presentation.screen.height.HeightScreen
import com.plcoding.onboarding_presentation.screen.nutrient_goal.NutrientGoalScreen
import com.plcoding.onboarding_presentation.screen.weight.WeightScreen
import com.plcoding.onboarding_presentation.screen.weight.WeightViewModel
import com.plcoding.onboarding_presentation.screen.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldStat = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldStat,
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(route = Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.AGE) {
                            AgeScreen(scaffoldStat, navController::navigate)
                        }
                        composable(route = Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.HEIGHT) {
                            HeightScreen(scaffoldStat = scaffoldStat, navController::navigate)
                        }
                        composable(route = Route.WEIGHT) {
                            WeightScreen(scaffoldStat = scaffoldStat, navController::navigate)
                        }
                        composable(route = Route.ACTIVITY) {
                            ActivityLevelScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldStat,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.TRACKER_OVERVIEW) {

                        }

                        composable(route = Route.SEARCH) {

                        }


                    }
                }


            }
        }
    }
}