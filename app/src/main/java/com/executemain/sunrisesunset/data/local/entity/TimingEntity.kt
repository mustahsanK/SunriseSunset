package com.executemain.sunrisesunset.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimingEntity(
    @PrimaryKey
    val date: String,
    val sunrise: String,
    val sunset: String,
    val firstLight: String,
    val lastLight: String,
    val dawn: String,
    val dusk: String,
    val solarNoon: String,
    val goldenHour: String,
    val dayLength: String,
    val timezone: String,
    val utc: Int,
)