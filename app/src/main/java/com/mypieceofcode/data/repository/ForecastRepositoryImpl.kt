package com.mypieceofcode.data.repository

import androidx.lifecycle.LiveData
import com.mypieceofcode.data.db.CurrentLocationDao
import com.mypieceofcode.data.db.CurrentWeatherDao
import com.mypieceofcode.data.db.entity.WeatherLocation
import com.mypieceofcode.data.db.unlocalized.current.UnitSpecifyCurrentWeatherEntry
import com.mypieceofcode.data.network.WeatherNetworkDatasource
import com.mypieceofcode.data.network.response.CurrentWeatherResponse
import com.mypieceofcode.data.providers.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val currentLocationDao: CurrentLocationDao,
    private val weatherNetworkDatasource: WeatherNetworkDatasource,
    private val locationProvider: LocationProvider
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

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO){
            return@withContext currentLocationDao.getCurrentLocation()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            currentLocationDao.upsert(fetchedWeather.location)
        }
    }

    private suspend fun initWeatherData(){
        val lastWeatherLocation = currentLocationDao.getCurrentLocation().value

        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)){
            fetchCurrentWeather()
            return
        }

        if (isFetchCurrentNeeded(lastWeatherLocation.zonedDataTime)){
            fetchCurrentWeather()
        }
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDatasource.fetchCurrentWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastTimeFetch: ZonedDateTime) : Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastTimeFetch.isBefore(thirtyMinutesAgo)
    }
}