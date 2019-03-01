package com.mypieceofcode.ui.weather.current

import androidx.lifecycle.ViewModel
import com.mypieceofcode.data.providers.UnitProvider
import com.mypieceofcode.data.repository.ForecastRepository
import com.mypieceofcode.internal.UnitSystem
import com.mypieceofcode.internal.lazyDeferred

class CurrentWeatherViewModel (
    private val repository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        repository.getCurrentWeather(isMetric)
    }
}
