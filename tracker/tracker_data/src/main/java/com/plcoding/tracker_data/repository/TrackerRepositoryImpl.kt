package com.plcoding.tracker_data.repository

import com.plcoding.tracker_data.local.TrackerDao
import com.plcoding.tracker_data.mapper.toTrackableFood
import com.plcoding.tracker_data.mapper.toTrackedEntity
import com.plcoding.tracker_data.mapper.toTrackedFood
import com.plcoding.tracker_data.remote.OpenFoodApi
import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImpl @Inject constructor(
    private val trackerDao: TrackerDao,
    private val apiService: OpenFoodApi
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        val searchDto = apiService.searchFood(query, page, pageSize)
        return try {
            val result: List<TrackableFood> = searchDto.products.mapNotNull { it.toTrackableFood() }
            Result.success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) =
        trackerDao.insertTrackedFood(trackedFood.toTrackedEntity())

    override suspend fun deleteTrackedFood(food: TrackedFood) =
        trackerDao.deleteTrackedFood(food.toTrackedEntity())

    override fun getFoodsForDate(localeData: LocalDate): Flow<List<TrackedFood>> =
        trackerDao.getFoodsForDate(
            day = localeData.dayOfMonth,
            month = localeData.monthValue,
            year = localeData.year
        )
            .map { entities ->
                entities.map { it.toTrackedFood() }
            }
}