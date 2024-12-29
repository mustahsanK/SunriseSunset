package com.funcexec.sunrisesunset.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RootDto(
    @Json(name = "results")
    val dto: TimingDto
)
