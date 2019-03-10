package idd.urbanido.interfaces.fragments

import android.content.Context
import idd.urbanido.model.IdResponse
import idd.urbanido.model.QuotesResponse
import io.reactivex.Observable

interface QuotesMVP {
    interface View {
        fun setupMVP()
        fun saveId(id: Int)
        fun showQuotes(quotes: List<QuotesResponse>)
        fun showSnack(text: String)
    }

    interface Presenter {
        fun updateQuotes(quotes: List<QuotesResponse>)
        fun getData(context: Context, token: String)
        fun getId(context: Context, token: String)
    }

    interface Repository {
        fun getQuotesListObservable(context: Context, token: String)
                : Observable<List<QuotesResponse>>?
        fun getUserIdObservable(context: Context, token: String)
                : Observable<IdResponse>?
    }
}