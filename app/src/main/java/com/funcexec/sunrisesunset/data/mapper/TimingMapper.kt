package com.funcexec.sunrisesunset.data.mapper

import com.funcexec.sunrisesunset.data.converter.toDate
import com.funcexec.sunrisesunset.data.converter.toTime
import com.funcexec.sunrisesunset.data.local.entity.TimingEntity
import com.funcexec.sunrisesunset.data.remote.dto.TimingDto
import com.funcexec.sunrisesunset.domain.model.Timing

fun TimingDto.toTimingEntity(): TimingEntity {
    return TimingEntity(
        date = date,
        sunrise = sunrise,
        sunset = sunset,
        firstLight = firstLight,
        lastLight = lastLight,
        dawn = dawn,
        dusk = dusk,
        solarNoon = solarNoon,
        goldenHour = goldenHour,
        dayLength = dayLength,
        timezone = timezone,
        utc = utc,
    )
}

fun TimingEntity.toTiming(): Timing {
    return Timing(
        date = date.toDate(),
        sunrise = sunrise.toTime(),
        sunset = sunset.toTime(),
        firstLight = firstLight.toTime(),
        lastLight = lastLight.toTime(),
        dawn = dawn.toTime(),
        dusk = dusk.toTime(),
        solarNoon = solarNoon.toTime(),
        goldenHour = goldenHour.toTime(),
        dayLength = dayLength,
        timezone = timezone,
        utc = utc
    )
}