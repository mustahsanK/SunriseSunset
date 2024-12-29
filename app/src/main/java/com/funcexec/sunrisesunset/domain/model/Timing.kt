package com.funcexec.sunrisesunset.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Timing(
    val date: LocalDate,
    val sunrise: LocalTime,
    val sunset: LocalTime,
    val firstLight: LocalTime,
    val lastLight: LocalTime,
    val dawn: LocalTime,
    val dusk: LocalTime,
    val solarNoon: LocalTime,
    val goldenHour: LocalTime,
    val dayLength: String,
    val timezone: String,
    val utc: Int,
)