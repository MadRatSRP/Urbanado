package idd.urbanido.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import idd.urbanido.R
import idd.urbanido.interfaces.fragments.ProfileQuoteMVP
import idd.urbanido.presenters.fragments.ProfileQuotePresenter
import idd.urbanido.repositories.ProfileQuoteRepository
import idd.urbanido.util.MyApplication
import kotlinx.android.synthetic.main.fragment_profile_quote.*
import ui.util.logd

class ProfileQuote: Fragment(), ProfileQuoteMVP.View {
    var profileQuotePresenter: ProfileQuotePresenter? = null

    companion object { val instance = ProfileQuote() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        var myApplication = MyApplication.instance
        var token = myApplication.releaseToken()
        token?.let { logd(it) }

        context?.let { token?.let { it1 -> profileQuotePresenter?.getData(it, it1) } }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bundle = arguments

        (activity as AppCompatActivity).supportActionBar?.title = bundle?.getString("title")
        val view = inflater.inflate(R.layout.fragment_profile_quote, container, false)

        return view
    }

    override fun setupMVP() {
        profileQuotePresenter = ProfileQuotePresenter(this, ProfileQuoteRepository())
    }

    override fun showProfileQuote(avprice: String, date: String) {
        pqAvPrice.text = avprice
        pqDate.text = date
    }
}