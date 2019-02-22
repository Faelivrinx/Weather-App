package com.mypieceofcode.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mypieceofcode.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "5578fe50317d423b824190835192002"

//http://api.apixu.com/v1/current.json?key=5578fe50317d423b824190835192002&q=London


interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q" )location: String,
        @Query("lang") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : ApixuWeatherApiService {
            val requestInterceptor = Interceptor {
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()
                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()
                it.proceed(request)
            }
            val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl("http://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }
    }
}