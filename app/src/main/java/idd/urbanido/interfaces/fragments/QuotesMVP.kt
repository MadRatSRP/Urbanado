package idd.urbanido.interfaces.fragments

import android.content.Context
import idd.urbanido.model.QuotesResponse
import io.reactivex.Observable

interface QuotesMVP {
    interface View {
        fun setupMVP()
        fun showQuotes(quotes: List<QuotesResponse>)
        //fun setupMVP()
        //fun showProfile(name: String, email: String, password: String, phone: String)
    }

    interface Presenter {
        fun updateQuotes(quotes: List<QuotesResponse>)
        fun getData(context: Context, token: String)
    }

    interface Repository {
        fun getQuotesListObservable(context: Context, token: String): Observable<List<QuotesResponse>>?
    }
}