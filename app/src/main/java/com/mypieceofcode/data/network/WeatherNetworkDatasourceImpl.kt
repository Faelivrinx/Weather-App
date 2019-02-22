package com.mypieceofcode.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mypieceofcode.data.network.response.CurrentWeatherResponse
import com.mypieceofcode.internal.NoConnectivityExpection

class WeatherNetworkDatasourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDatasource {

    private var _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location, languageCode)
                .await()
            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        } catch (e: NoConnectivityExpection){
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}