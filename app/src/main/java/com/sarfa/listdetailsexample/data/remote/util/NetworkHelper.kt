package com.sarfa.listdetailsexample.data.remote.util

import android.content.Context
import javax.inject.Inject

class NetworkHelper @Inject constructor(private val context: Context) :
    BaseNetworkHelper {
    @Override
    override fun hasInternetConnection() = context.hasInternetConnection()
}

class FakeNetworkHelper @Inject constructor(private val hasConnection: Boolean) :
    BaseNetworkHelper {
    @Override
    override fun hasInternetConnection() = hasConnection
}

interface BaseNetworkHelper {
    fun hasInternetConnection(): Boolean
}