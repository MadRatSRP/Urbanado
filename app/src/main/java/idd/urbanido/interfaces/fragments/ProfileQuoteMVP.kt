package idd.urbanido.interfaces.fragments

import android.content.Context
import idd.urbanido.model.ProfileQuoteResponse
import io.reactivex.Observable

interface ProfileQuoteMVP {
    interface View {
        fun setupMVP()
        fun showProfileQuote(avprice: String, date: String)
    }

    interface Presenter {
        fun updateProfileQuote(list: List<ProfileQuoteResponse>)
        fun getData(context: Context, id: String, token: String)
    }

    interface Repository {
        fun getProfileQuoteObservable(context: Context, id: String, token: String)
                : Observable<List<ProfileQuoteResponse>>?
    }
}