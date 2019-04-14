package com.mypieceofcode.data.network.response

import com.google.gson.annotations.SerializedName
import com.mypieceofcode.data.db.entity.FutureWeatherEntry

data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>?)