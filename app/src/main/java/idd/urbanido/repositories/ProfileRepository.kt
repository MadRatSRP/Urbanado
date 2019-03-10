package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.ProfileMVP
import idd.urbanido.model.profile.ProfileResponse
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call

class ProfileRepository: ProfileMVP.Repository {
    override fun getProfileObservable(context: Context, id: String, token: String)
            : Observable<ProfileResponse>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getProfile(token, id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateProfileCall(context: Context, id: String,
                                   token: String, profileResponse: ProfileResponse)
            : Call<ProfileResponse>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.updateProfile(token, id, profileResponse)
    }
}