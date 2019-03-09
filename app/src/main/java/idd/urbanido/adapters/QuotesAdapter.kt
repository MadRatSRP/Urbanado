package idd.urbanido.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import idd.urbanido.R
import idd.urbanido.model.QuotesResponse

class QuotesAdapter(private val context: Context): RecyclerView.Adapter<QuotesAdapter.UsersHolder>() {

    private val quotes = ArrayList<QuotesResponse>()

    fun updateQuotesList(new_quotes: List<QuotesResponse>) {
        quotes.clear()
        quotes.addAll(new_quotes)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.list_quotes, parent, false)
        return UsersHolder(view)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.name.text = quotes[position].name

        holder.name.setOnClickListener {v->
            var bundle = Bundle()
            bundle.putString("title", holder.name.text.toString())

            Navigation.findNavController(v).navigate(R.id.profileQuote, bundle)
        }

        holder.price.text = quotes[position].price
    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    inner class UsersHolder internal constructor(view: View):
                RecyclerView.ViewHolder(view) {
        internal val name: TextView = view.findViewById(R.id.nameValue)
        internal val price: TextView = view.findViewById(R.id.priceValue)
    }
}