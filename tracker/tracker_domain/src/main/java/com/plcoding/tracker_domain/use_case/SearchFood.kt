package com.plcoding.tracker_domain.use_case

import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.repository.TrackerRepository
import javax.inject.Inject

class SearchFood @Inject constructor(
    private val repository:TrackerRepository
) {
    suspend operator fun invoke(
        query:String,
        page:Int = 1,
        pageSize:Int = 40
    ):Result<List<TrackableFood>>{
        return repository.searchFood(query, page, pageSize)
    }
}