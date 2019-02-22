package com.mypieceofcode.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.mypieceofcode.R
import com.mypieceofcode.data.network.ApixuWeatherApiService
import com.mypieceofcode.data.network.ConnectivityInterceptorImpl
import com.mypieceofcode.data.network.WeatherNetworkDatasource
import com.mypieceofcode.data.network.WeatherNetworkDatasourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel
        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.context!!))
        val weatherNetworkDatasourceImpl = WeatherNetworkDatasourceImpl(apiService)

        weatherNetworkDatasourceImpl.downloadedCurrentWeather.observe(this, Observer {
            textView.text = it.currentWeatherEntry.toString()
        })
        GlobalScope.launch(Dispatchers.Main){
            weatherNetworkDatasourceImpl.fetchCurrentWeather("London", "en")
        }
    }

}
