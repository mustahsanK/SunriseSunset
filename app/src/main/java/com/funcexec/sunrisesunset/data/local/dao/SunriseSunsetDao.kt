package com.funcexec.sunrisesunset.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.funcexec.sunrisesunset.data.local.entity.TimingEntity

@Dao
interface SunriseSunsetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTiming(
        timing: TimingEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTimings(
        timings: List<TimingEntity>
    )

    @Query("SELECT * FROM timingentity WHERE date = :date LIMIT 1")
    suspend fun findTimingByDate (
        date: String
    ): TimingEntity

    @Query("SELECT * FROM timingentity")
    suspend fun findAllTimings(): List<TimingEntity>

    @Query("DELETE FROM timingentity")
    suspend fun clearTimings()
}