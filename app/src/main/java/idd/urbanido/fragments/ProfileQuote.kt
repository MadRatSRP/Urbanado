package idd.urbanido.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import idd.urbanido.R

class ProfileQuote: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bundle = arguments

        (activity as AppCompatActivity).supportActionBar?.title = bundle?.getString("title")
        val view = inflater.inflate(R.layout.fragment_profile_quote, container, false)

        return view
    }

    companion object {
        //private var baseFragment: ProfileForeignView? = ProfileForeignView()

        val instance = ProfileQuote()
    }
}