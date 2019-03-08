package idd.urbanido.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import idd.urbanido.R
import idd.urbanido.interfaces.fragments.AuthorizationMVP
import idd.urbanido.presenters.fragments.AuthorizationPresenter
import idd.urbanido.repositories.AuthorizationRepository
import kotlinx.android.synthetic.main.fragment_authorization.*
import ui.util.logd

class Authorization: Fragment(), AuthorizationMVP.View {

    private var token: String? = null
    private val bundle: Bundle? = null

    private var authorizationPresenter: AuthorizationPresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()


        authorizeUser.setOnClickListener {
            context?.let {

                authorizationPresenter?.authorizeUser(it, email, password)
            }
        }


        bundle?.putString("token", token)
        bundle?.putBundle("authBundle", bundle)

        registerYourself.setOnClickListener {view->
            Navigation.findNavController(view).navigate(R.id.action_authorization_to_registration)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun setupMVP() {
        authorizationPresenter = AuthorizationPresenter(this, AuthorizationRepository())
    }

    override fun saveToken(token: String) {
        //this.token = token.substring(9, token.length - 1)
        this.token = token
        Log.d("HI", this.token)
    }

    override fun moveToQuotes() {
        token?.let { logd(it) }

        view?.let { Navigation.findNavController(it).navigate(R.id.action_authorization_to_quotes,
                                                              bundle) }
    }

    override fun showSnack(text: String){
        view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }
    }
}