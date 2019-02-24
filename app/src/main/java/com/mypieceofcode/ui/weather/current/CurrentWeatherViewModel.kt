package com.mypieceofcode.ui.weather.current

import androidx.lifecycle.ViewModel
import com.mypieceofcode.data.repository.ForecastRepository
import com.mypieceofcode.internal.UnitSystem
import com.mypieceofcode.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val repository: ForecastRepository
) : ViewModel() {
    val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        repository.getCurrentWeather(isMetric)
    }
}
