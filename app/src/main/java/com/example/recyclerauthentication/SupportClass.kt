package com.example.recyclerauthentication

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

import android.widget.Toast


class SupportClass {

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    fun checkPhone(inputPhone: String): Boolean {
        var status = false
        if (inputPhone.length < 11)
            status = false
        else when (inputPhone) {
            "79005868675" -> status = true
            "449009223321" -> status = true
            "375663211234" -> status = true
            else -> status = false
        }
        return  status
    }

    fun checkPassword(inputPass: String): Boolean {
        val rightPass: String = "devExam18"
        if (inputPass.length < 9) {
            return false
        } else if (inputPass == rightPass)
            return true
        else return false
    }

    fun makeToast(context: Context, string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}