package com.funcexec.sunrisesunset.data.remote

import com.funcexec.sunrisesunset.data.remote.dto.RootDto
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface SunriseSunsetAPI {

    @GET("json")
    suspend fun getTodayTiming(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("date") date: LocalDate,
        @Query("time_format") format: Int = 24,
    ): RootDto?

    @GET("json")
    suspend fun getTimings(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("date_start") startDate: LocalDate,
        @Query("date_end") endDate: LocalDate,
        @Query("time_format") format: Int = 24,
    ): List<RootDto>?

    companion object {
        const val BASE_URL = "https://api.sunrisesunset.io"
    }
}