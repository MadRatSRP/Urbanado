package idd.urbanido.util

import android.app.Application

class MyApplication : Application() {
    var id: Int? = null
    var token: String? = null

    companion object {
        val instance = MyApplication()
    }

    fun saveId(id: Int) {
        this.id = id
    }

    fun saveToken(token: String) {
        this.token = token
    }

    fun releaseId(): Int? {
        return id
    }

    fun releaseToken(): String? {
        return token
    }
}