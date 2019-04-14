package com.mypieceofcode.data.network.response

import com.google.gson.annotations.SerializedName
import com.mypieceofcode.data.db.entity.WeatherLocation

data class FutureWeatherResponse(
    val location: WeatherLocation,
    @SerializedName("forecast")
    val featureWeatherEntries: ForecastDaysContainer)