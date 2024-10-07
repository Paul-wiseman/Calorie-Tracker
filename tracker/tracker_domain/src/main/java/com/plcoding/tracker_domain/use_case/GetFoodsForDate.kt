package com.plcoding.tracker_domain.use_case

import com.plcoding.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import javax.inject.Inject

class GetFoodsForDate @Inject constructor(
    private val repository: TrackerRepository
) {

    operator fun invoke(
        date: LocalDate
    ) = repository.getFoodsForDate(
        date
    )
}