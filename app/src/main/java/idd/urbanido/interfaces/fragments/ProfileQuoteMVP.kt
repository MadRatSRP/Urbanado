package idd.urbanido.interfaces.fragments

import android.content.Context
import idd.urbanido.model.ProfileQuoteResponse
import io.reactivex.Observable

interface ProfileQuoteMVP {
    interface View {
    }

    interface Presenter {
    }

    interface Repository {
        fun getProfileQuoteObservable(context: Context, token: String): Observable<ProfileQuoteResponse>?
    }
}