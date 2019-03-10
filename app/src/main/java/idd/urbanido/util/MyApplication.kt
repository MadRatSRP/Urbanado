package idd.urbanido.util

import android.app.Application
import ui.util.logd

class MyApplication : Application() {
    var id: Int? = null
    var token: String? = null

    companion object {
        val instance = MyApplication()
    }

    fun saveId(id: Int) {
        this.id = id
        logd("ID пользователя сохранён, " + this.id.toString())
    }

    fun saveToken(token: String) {
        this.token = token
        logd("Token пользователя сохранён, " + this.token.toString())
    }

    fun releaseId(): Int? {
        return id
    }

    fun releaseToken(): String? {
        return token
    }
}