package com.plcoding.tracker_presentation.tracker_overview

import com.plcoding.tracker_domain.model.TrackedFood

sealed class TrackerOverViewEvent {
    object OnNextDayClicked : TrackerOverViewEvent()
    object OnPreviousDayClicked : TrackerOverViewEvent()
    data class OnToggleMealClick(val meal: Meal) : TrackerOverViewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) :
        TrackerOverViewEvent()

    data class OnAddFoodClick(val meal: Meal) : TrackerOverViewEvent()
}