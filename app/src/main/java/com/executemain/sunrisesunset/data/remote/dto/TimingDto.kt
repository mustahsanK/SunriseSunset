package com.executemain.sunrisesunset.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimingDto(
    @Json(name = "date")
    val date: String,

    @Json(name = "sunrise")
    val sunrise: String,

    @Json(name = "sunset")
    val sunset: String,

    @Json(name = "first_light")
    val firstLight: String,

    @Json(name = "last_light")
    val lastLight: String,

    @Json(name = "dawn")
    val dawn: String,

    @Json(name = "dusk")
    val dusk: String,

    @Json(name = "solar_noon")
    val solarNoon: String,

    @Json(name = "golden_hour")
    val goldenHour: String,

    @Json(name = "day_length")
    val dayLength: String,

    @Json(name = "timezone")
    val timezone: String,

    @Json(name = "utc_offset")
    val utc: Int,
)
