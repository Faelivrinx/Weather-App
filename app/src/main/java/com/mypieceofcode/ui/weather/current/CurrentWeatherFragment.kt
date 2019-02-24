package com.mypieceofcode.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.mypieceofcode.R
import com.mypieceofcode.internal.glide.GlideApp
import com.mypieceofcode.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

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
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if(it == null)return@Observer

            group_loading.visibility = View.GONE

            updateLocation("Cisiec")
            updateToToday()
            updateTemperatures(it.temperature, it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection, it.windSpeed)
            updateVisibility(it.visibilityDistance)

            GlideApp.with(this@CurrentWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(image_condition_icon)

        })
    }

    private fun chooseLocalizedUnitAbbrevitation(metric: String, imperial: String) : String {
        return if (viewModel.isMetric) metric else imperial
    }

    private fun updateLocation(location: String) {
        (activity as AppCompatActivity).supportActionBar?.title = location
    }
    private fun updateToToday() {
        (activity as AppCompatActivity).supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbrevitation = chooseLocalizedUnitAbbrevitation("°C", "°F")
        textView_temperature.text = "$temperature$unitAbbrevitation"
        textView_feels_like_temperature.text = "Odczuwalna temp: $feelsLike$unitAbbrevitation"
    }

    private fun updateCondition(condition: String) {
        textView_condition.text  = condition
    }

    private fun updatePrecipitation(precipitation: Double) {
        val unitAbbrevitation = chooseLocalizedUnitAbbrevitation("mm", "in")
        textView_precipitation.text = "Opady: $precipitation $unitAbbrevitation"

    }

    private fun updateWind(windDirection: String, windSpeed: Double) {
        val unitAbbrevitation = chooseLocalizedUnitAbbrevitation("kph", "mph")
        textView_wind.text = "Wiatr: $windDirection $windSpeed $unitAbbrevitation"
    }

    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbrevitation("km", "mi.")
        textView_visibility.text = "Widoczność: $visibilityDistance $unitAbbreviation"
    }
}
