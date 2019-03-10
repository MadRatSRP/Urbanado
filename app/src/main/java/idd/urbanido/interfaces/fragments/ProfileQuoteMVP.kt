package idd.urbanido.interfaces.fragments

import android.content.Context
import com.github.mikephil.charting.data.BarEntry
import idd.urbanido.model.ProfileQuoteResponse
import io.reactivex.Observable

interface ProfileQuoteMVP {
    interface View {
        fun setupMVP()
        fun showProfileQuote(array1: ArrayList<BarEntry>, array2: ArrayList<String>)
    }

    interface Presenter {
        fun updateProfileQuote(entries: ArrayList<BarEntry>, labels: ArrayList<String>)
        fun getData(context: Context, id: String, token: String)
    }

    interface Repository {
        fun getProfileQuoteObservable(context: Context, id: String, token: String)
                : Observable<List<ProfileQuoteResponse>>?
    }
}