package com.plcoding.tracker_domain.di

import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.tracker_domain.repository.TrackerRepository
import com.plcoding.tracker_domain.use_case.CalculateMealNutrients
import com.plcoding.tracker_domain.use_case.DeleteTrackedFood
import com.plcoding.tracker_domain.use_case.GetFoodsForDate
import com.plcoding.tracker_domain.use_case.SearchFood
import com.plcoding.tracker_domain.use_case.TrackFood
import com.plcoding.tracker_domain.use_case.TrackerDomainUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @ViewModelScoped
    fun providerTrackerDomainUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ) = TrackerDomainUseCases(
        calculateMealNutrients = CalculateMealNutrients(preferences),
        deleteTrackedFood = DeleteTrackedFood(repository),
        getFoodsForDate = GetFoodsForDate(repository),
        searchFood = SearchFood(repository),
        trackFood = TrackFood(repository)

    )
}