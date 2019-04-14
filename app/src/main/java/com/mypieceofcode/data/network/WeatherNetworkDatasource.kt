package com.mypieceofcode.data.network

import androidx.lifecycle.LiveData
import com.mypieceofcode.data.network.response.CurrentWeatherResponse
import com.mypieceofcode.data.network.response.FutureWeatherResponse

interface WeatherNetworkDatasource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadedFeatureWeather: LiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )

    suspend fun fetchFutureWeather(
        location: String,
        languageCode: String
    )
}