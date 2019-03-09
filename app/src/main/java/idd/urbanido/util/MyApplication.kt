package idd.urbanido.util

import android.app.Application

class MyApplication : Application() {
    var token: String? = null

    companion object {
        val instance = MyApplication()
    }

    fun saveToken(token: String) {
        this.token = token
    }

    fun releaseToken(): String? {
        return token
    }
}