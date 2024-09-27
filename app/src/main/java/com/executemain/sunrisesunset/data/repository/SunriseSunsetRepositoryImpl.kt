package com.executemain.sunrisesunset.data.repository

import com.executemain.sunrisesunset.data.local.SunriseSunsetDatabase
import com.executemain.sunrisesunset.data.mapper.toTiming
import com.executemain.sunrisesunset.data.mapper.toTimingEntity
import com.executemain.sunrisesunset.data.remote.SunriseSunsetAPI
import com.executemain.sunrisesunset.domain.model.Timing
import com.executemain.sunrisesunset.domain.repository.SunriseSunsetRepository
import com.executemain.sunrisesunset.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SunriseSunsetRepositoryImpl @Inject constructor(
    private val api: SunriseSunsetAPI,
    private val db: SunriseSunsetDatabase
): SunriseSunsetRepository {

    private val dao = db.dao

    override suspend fun getTimings(
        fetchFromRemote: Boolean,
        latitude: Double,
        longitude: Double,
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<Resource<List<Timing>>> {
        return flow {
            emit(Resource.Loading(true))

            val localTimings = dao.findAllTimings()
            val shouldLoadFromCache = localTimings.isNotEmpty() && !fetchFromRemote

            if (shouldLoadFromCache) {
                emit(Resource.Success(
                    data = localTimings.map {
                        it.toTiming()
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteTimings = try {
                api.getTimings(
                    latitude = latitude,
                    longitude = longitude,
                    startDate = startDate,
                    endDate = endDate,
                )
            } catch (e: HttpException) {
                emit(Resource.Error(message = "HTTP Exception"))
                return@flow
            } catch (e: IOException) {
                emit(Resource.Error(message = "IO Exception"))
                return@flow
            } catch (e: Exception) {
                emit(Resource.Error(message = "An Error Occurred"))
                return@flow
            }

            remoteTimings?.let { timings ->
                val timingEntities = timings.map {
                    it.dto.toTimingEntity()
                }
                dao.clearTimings()
                dao.insertAllTimings(timingEntities)
                emit(Resource.Success(
                    data = timingEntities.map {
                        it.toTiming()
                    }
                ))
                emit(Resource.Loading(isLoading = false))
            }
            return@flow
        }
    }

    override suspend fun getTiming(
        fetchFromRemote: Boolean,
        latitude: Double,
        longitude: Double,
        date: LocalDate
    ): Flow<Resource<Timing>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val localTiming = dao.findTimingByDate(date.toString())
            val shouldLoadFromCache = !fetchFromRemote

            if (shouldLoadFromCache) {
                emit(Resource.Success(
                    localTiming.toTiming()
                ))
                emit(Resource.Loading(isLoading = false))
            }

            val remoteTiming = try {
                api.getTodayTiming(
                    latitude = latitude,
                    longitude = longitude,
                    date = date,
                )
            } catch (e: HttpException) {
                emit(Resource.Error(message = "HTTP Exception"))
                return@flow
            } catch (e: IOException) {
                emit(Resource.Error(message = "IO Exception"))
                return@flow
            } catch (e: Exception) {
                emit(Resource.Error(message = "An Error Occurred"))
                return@flow
            }

            remoteTiming?.let { timing ->
                val timingEntity = timing.dto.toTimingEntity()
                dao.insertTiming(timingEntity)
                emit(Resource.Success(
                    data = timingEntity.toTiming()
                ))
                emit(Resource.Loading(isLoading = false))
            }
            return@flow
        }
    }
}