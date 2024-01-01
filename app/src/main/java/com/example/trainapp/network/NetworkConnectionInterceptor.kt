package com.example.trainapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

/**
 * An OkHttp interceptor that checks for network connectivity before proceeding with a network request.
 * If there is no network connectivity, it throws an IOException to signify the absence of a network connection.
 *
 * @property context The context used for checking the network connectivity status.
 */
class NetworkConnectionInterceptor(val context: Context) : Interceptor {
    /**
     * Intercepts and processes the network request.
     * Checks for network connectivity before allowing the request to proceed. If there is no connectivity, it logs the information and throws an IOException.
     *
     * @param chain The chain of request and response interceptors.
     * @return The response from the chain if there is network connectivity.
     * @throws IOException if there is no network connectivity.
     */
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            if (!isConnected(context=context)) {
                Log.i("retrofit", "there is no connection")
                throw IOException()
            } else {
                val builder = chain.request().newBuilder()
                return@run chain.proceed(builder.build())
            }
        }

    /**
     * Checks whether there is network connectivity.
     *
     * @param context The context used to access system services for network connectivity.
     * @return True if there is network connectivity, false otherwise.
     */
    fun isConnected(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result =
                when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result =
                        when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }
                }
            }
        }

        return result
    }
}
