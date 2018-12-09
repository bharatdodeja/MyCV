package com.bharatdodeja.mycv.framework.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Utility class for network related stuff
 */
class NetworkUtils(private val context: Context) {

    /**
     * Checks whether device is connected to active network or not
     */
    fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as? ConnectivityManager
        connectivityManager?.let { it ->
            val netInfo = it.activeNetworkInfo
            netInfo?.let {
                if (it.isConnected) return true
            }
        }
        return false
    }
}