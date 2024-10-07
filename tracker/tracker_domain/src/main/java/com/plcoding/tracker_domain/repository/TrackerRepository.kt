package com.plcoding.tracker_domain.repository

import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(trackedFood: TrackedFood)
    suspend fun deleteTrackedFood(food: TrackedFood)
    fun getFoodsForDate(localeData: LocalDate): Flow<List<TrackedFood>>
}