package com.mypieceofcode.ui.weather.feature.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mypieceofcode.R

class FeatureListWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = FeatureListWeatherFragment()
    }

    private lateinit var viewModel: FeatureListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feature_list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeatureListWeatherViewModel::class.java)
    }

}
