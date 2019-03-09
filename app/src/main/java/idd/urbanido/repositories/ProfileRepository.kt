package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.ProfileMVP
import idd.urbanido.model.Profile
import idd.urbanido.model.ProfileResponse
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

class ProfileRepository: ProfileMVP.Repository {
    override fun getProfileObservable(context: Context, token: String)
            : Observable<ProfileResponse>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getProfile(token, "21")
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateProfileCall(context: Context, token: String, profile: Profile)
            : Call<Profile>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.updateProfile(token, "21", profile)
    }
}