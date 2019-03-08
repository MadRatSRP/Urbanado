package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.QuotesMVP
import idd.urbanido.model.ProfileResponse
import idd.urbanido.model.QuotesResponse
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuotesRepository: QuotesMVP.Repository {
    override fun getQuotesListObservable(context: Context): Observable<List<QuotesResponse>>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getQuotesList()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }
}