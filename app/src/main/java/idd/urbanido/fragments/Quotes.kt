package idd.urbanido.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import idd.urbanido.R
import idd.urbanido.adapters.QuotesAdapter
import idd.urbanido.interfaces.fragments.QuotesMVP
import idd.urbanido.model.QuotesResponse
import idd.urbanido.presenters.fragments.QuotesPresenter
import idd.urbanido.repositories.QuotesRepository
import kotlinx.android.synthetic.main.fragment_quotes.*
import kotlinx.android.synthetic.main.fragment_quotes.view.*
import ui.util.linearManager

class Quotes: Fragment(), QuotesMVP.View {
    private var quotesAdapter: QuotesAdapter? = null

    private var quotesPresenter: QuotesPresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        recyclerViewQuotes.linearManager()

        /*usersShowUserInformation.setOnClickListener {
            //    context?.let { usersPresenter?.getUserInformation(it) }
        }

        usersShowUsersList.setOnClickListener {
            context?.let { usersPresenter?.getUsersList(it) }
        }*/

        context?.let { quotesPresenter?.getData(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_quotes, container, false)

        context?.let { quotesAdapter = QuotesAdapter(it) }

        view.recyclerViewQuotes.adapter = quotesAdapter

        return view
    }

    override fun setupMVP() {
        quotesPresenter = QuotesPresenter(this, QuotesRepository())
    }

    override fun showQuotes(quotes: List<QuotesResponse>) {
         quotesAdapter?.updateQuotesList(quotes)
         recyclerViewQuotes.adapter = quotesAdapter
    }
}