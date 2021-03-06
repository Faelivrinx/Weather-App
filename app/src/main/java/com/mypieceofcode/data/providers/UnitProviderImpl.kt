package com.mypieceofcode.data.providers

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.mypieceofcode.internal.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProviderImpl(val context: Context) : PreferencesProvider(context), UnitProvider {

    override fun getUnitSystem(): UnitSystem {
        val selectedUnit = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.toString())
        return UnitSystem.valueOf(selectedUnit!!)
    }
}