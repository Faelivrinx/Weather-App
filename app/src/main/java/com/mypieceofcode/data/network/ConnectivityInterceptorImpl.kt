package com.mypieceofcode.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.telecom.ConnectionService
import com.mypieceofcode.internal.NoConnectivityExpection
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private var appContext  = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isOnline()){
            throw NoConnectivityExpection()
        }

        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && !networkInfo.isConnected
    }
}