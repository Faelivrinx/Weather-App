package com.mypieceofcode.data.repository

import androidx.lifecycle.LiveData
import com.mypieceofcode.data.db.unlocalized.UnitSpecifyCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecifyCurrentWeatherEntry>
}