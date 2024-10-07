package com.plcoding.tracker_data.di

import android.content.Context
import androidx.room.Room
import com.plcoding.tracker_data.local.TrackerDataBase
import com.plcoding.tracker_data.remote.OpenFoodApi
import com.plcoding.tracker_data.repository.TrackerRepositoryImpl
import com.plcoding.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Singleton
    @Provides
    fun providesOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }).build()

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): OpenFoodApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()

    @Singleton
    @Provides
    fun provideTrackerDataBase(
        @ApplicationContext context: Context
    ): TrackerDataBase =
        Room.databaseBuilder(
            context,
            TrackerDataBase::class.java,
            TRACKER_DATABASE
        ).build()

    @Provides
    @Singleton
    fun providesTrackerRepository(
        trackerDataBase: TrackerDataBase,
        openFoodApi: OpenFoodApi
    ): TrackerRepository = TrackerRepositoryImpl(
        trackerDao = trackerDataBase.dao,
        apiService = openFoodApi
    )

    private const val TRACKER_DATABASE = "tracker_db"
    private const val BASE_URL = "https://us.openfoodfacts.org/"
}