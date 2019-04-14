package com.mypieceofcode.data.network.response

import com.google.gson.annotations.SerializedName
import com.mypieceofcode.data.db.entity.CurrentWeatherEntry
import com.mypieceofcode.data.db.entity.WeatherLocation

data class CurrentWeatherResponse(
    val location: WeatherLocation,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)