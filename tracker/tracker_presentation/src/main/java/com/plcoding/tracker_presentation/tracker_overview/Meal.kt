package com.plcoding.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.plcoding.core.util.UiText
import com.plcoding.tracker_domain.model.MealType

data class Meal(
    val name: UiText,
    @DrawableRes val drawable:Int,
    val mealType: MealType,
    val carbs:Int = 0,
    val protein:Int = 0,
    val fat:Int = 0,
    val calories:Int = 0,
    val isExpanded:Boolean = false
)
