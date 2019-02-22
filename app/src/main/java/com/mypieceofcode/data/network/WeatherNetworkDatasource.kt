package com.mypieceofcode.data.network

import androidx.lifecycle.LiveData
import com.mypieceofcode.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDatasource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}