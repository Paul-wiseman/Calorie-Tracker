package com.plcoding.tracker_domain.use_case

import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

class TrackFood @Inject constructor(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) = repository.insertTrackedFood(
        TrackedFood(
            name = food.name,
            imageUrl = food.imageUrl,
            calories = ((food.caloriesPer100g / 100f) * amount).roundToInt(),
            carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
            protein = ((food.proteinPer100g / 100f) * amount).roundToInt(),
            fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
            mealType = mealType,
            amount = amount,
            date = date
        )
    )
}