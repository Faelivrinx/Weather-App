package com.mypieceofcode.internal

import java.io.IOException
import java.lang.Exception

class NoConnectivityExpection : IOException()

class LocationPermissionNotGrantedExpection: Exception()