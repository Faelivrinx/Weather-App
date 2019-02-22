package com.mypieceofcode.ui.weather.feature.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mypieceofcode.R

class FeatureDetailWheatherFragment : Fragment() {

    companion object {
        fun newInstance() = FeatureDetailWheatherFragment()
    }

    private lateinit var viewModel: FeatureDetailWheatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feature_detail_wheather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeatureDetailWheatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
