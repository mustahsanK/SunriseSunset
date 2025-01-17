package com.funcexec.sunrisesunset.domain.repository

import com.funcexec.sunrisesunset.domain.model.Timing
import com.funcexec.sunrisesunset.util.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface SunriseSunsetRepository {

    suspend fun getTiming(
        fetchFromRemote: Boolean,
        latitude: Double,
        longitude: Double,
        date: LocalDate,
    ): Flow<Resource<Timing>>

    suspend fun getTimings(
        fetchFromRemote: Boolean,
        latitude: Double,
        longitude: Double,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<Resource<List<Timing>>>
}