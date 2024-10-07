package com.plcoding.tracker_data.sources

import com.plcoding.core.util.RepositorySource
import com.plcoding.tracker_data.remote.dto.SearchDto
import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow

class SearchFood(
    private val repository: TrackerRepository
) : RepositorySource<TrackableFood, SearchDto>() {
    override suspend fun getFlow(): Flow<SearchDto> {
        TODO("Not yet implemented")
    }

    override fun trace(eventPayload: SearchDto) {
        TODO("Not yet implemented")
    }

    override suspend fun map(eventPayload: SearchDto): TrackableFood {
        TODO("Not yet implemented")
    }

}