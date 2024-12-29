package com.funcexec.sunrisesunset.data.converter

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDate.formatToString(): String {
    return this.format(
        DateTimeFormatter.ofPattern(
            "yyyy-MM-dd"
        )
    )
}

fun String.toDate(): LocalDate {
    return LocalDate.parse(
        this,
        DateTimeFormatter.ofPattern(
            "yyyy-MM-dd"
        )
    )
}

fun String.toTime(): LocalTime {
    return LocalTime.of(
        (this[0] - '0') * 10 + (this[1] - '0'),
        (this[3] - '0') * 10 + (this[4] - '0'),
        (this[6] - '0') * 10 + (this[7] - '0'),
    )
}