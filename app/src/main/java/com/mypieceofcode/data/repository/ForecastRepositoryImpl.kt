package com.mypieceofcode.data.repository

import androidx.lifecycle.LiveData
import com.mypieceofcode.data.db.CurrentWeatherDao
import com.mypieceofcode.data.db.unlocalized.UnitSpecifyCurrentWeatherEntry
import com.mypieceofcode.data.network.WeatherNetworkDatasource
import com.mypieceofcode.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDatasource: WeatherNetworkDatasource
) : ForecastRepository {

    init {
        weatherNetworkDatasource.downloadedCurrentWeather.observeForever{
                newCurrentWeather -> persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecifyCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if(metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData(){
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1))){
            fetchCurrentWeather()
        }
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDatasource.fetchCurrentWeather(
            "Cisiec",
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastTimeFetch: ZonedDateTime) : Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastTimeFetch.isBefore(thirtyMinutesAgo)
    }
}