package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.ProfileQuoteMVP
import idd.urbanido.model.Jojo
import idd.urbanido.model.profile_quote.PercentResponse
import idd.urbanido.model.profile_quote.ProfileQuoteResponse
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileQuoteRepository: ProfileQuoteMVP.Repository {
    override fun getProfileQuoteObservable(context: Context, id: String, token: String)
            : Observable<List<ProfileQuoteResponse>>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getProfileQuote(token, id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }
    override fun getQuotePercentObservable(context: Context, token: String, id: String,
                                           start_date: String, finish_date: String)
            : Observable<Jojo>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getQuotePercent(token, id, start_date, finish_date)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }
}