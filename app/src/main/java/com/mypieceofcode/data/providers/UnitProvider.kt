package com.mypieceofcode.data.providers

import com.mypieceofcode.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem() : UnitSystem
}