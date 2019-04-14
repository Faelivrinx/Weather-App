package com.mypieceofcode.data.repository

import androidx.lifecycle.LiveData
import com.mypieceofcode.data.db.entity.WeatherLocation
import com.mypieceofcode.data.db.unlocalized.current.UnitSpecifyCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecifyCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}