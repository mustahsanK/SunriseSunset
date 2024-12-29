package com.funcexec.sunrisesunset.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.funcexec.sunrisesunset.data.local.dao.SunriseSunsetDao
import com.funcexec.sunrisesunset.data.local.entity.TimingEntity

@Database(
    entities = [TimingEntity::class],
    version = 1
)
abstract class SunriseSunsetDatabase: RoomDatabase() {
    abstract val dao: SunriseSunsetDao
}