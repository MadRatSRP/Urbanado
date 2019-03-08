package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.ProfileMVP
import idd.urbanido.model.ProfileResponse
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileRepository: ProfileMVP.Repository {
    override fun getProfileObservable(context: Context, token: String): Observable<ProfileResponse>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getProfile(token, "21")
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }
}