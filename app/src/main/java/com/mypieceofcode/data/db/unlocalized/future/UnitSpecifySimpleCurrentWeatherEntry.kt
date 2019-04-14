package com.mypieceofcode.data.db.unlocalized.future

import java.time.LocalDate

interface UnitSpecifySimpleCurrentWeatherEntry {
    val date: LocalDate
    val avgTemperature: Double
    val conditionText: String
    val conditionIconUrl: String
}