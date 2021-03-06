package com.mypieceofcode.data.db.unlocalized.future

import androidx.room.ColumnInfo
import java.time.LocalDate

class MetricSimpleFutureWeatherEntry(
    @ColumnInfo(name = "date")
    override val date: LocalDate,
    @ColumnInfo(name = "avgtempC")
    override val avgTemperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String
) : UnitSpecifySimpleCurrentWeatherEntry
 {
}