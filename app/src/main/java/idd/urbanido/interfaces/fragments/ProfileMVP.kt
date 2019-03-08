package idd.urbanido.interfaces.fragments

import android.content.Context
import idd.urbanido.model.ProfileResponse
import io.reactivex.Observable

interface ProfileMVP {
    interface View {
        fun setupMVP()
        fun showProfile(name: String, email: String, password: String, phone: String)
    }

    interface Presenter {
        fun updateProfile(name: String, email: String, password: String, phone: String)
        fun getData(context: Context, token: String)
    }

    interface Repository {
        fun getProfileObservable(context: Context, token: String): Observable<ProfileResponse>?
    }
}