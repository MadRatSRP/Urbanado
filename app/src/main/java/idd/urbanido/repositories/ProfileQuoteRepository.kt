package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.ProfileQuoteMVP
import idd.urbanido.model.ProfileQuoteResponse
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileQuoteRepository: ProfileQuoteMVP.Repository {
    override fun getProfileQuoteObservable(context: Context, token: String): Observable<List<ProfileQuoteResponse>>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getProfileQuote(token, "1")
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }
}