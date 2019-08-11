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
import idd.urbanido.util.MyApplication
import kotlinx.android.synthetic.main.fragment_authorization.*
import ui.util.logd

class Authorization: Fragment(), AuthorizationMVP.View {

    private var token: String? = null

    private var authorizationPresenter: AuthorizationPresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        authorizeUser.setOnClickListener {
            context?.let {
                authorizationPresenter?.authorizeUser(it, email, password)
            }
        }

        registerYourself.setOnClickListener {view->
            Navigation.findNavController(view).navigate(R.id.action_authorization_to_registration)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun setupMVP() {
        authorizationPresenter = AuthorizationPresenter(this, AuthorizationRepository())
    }

    override fun saveToken(token: String) {
        this.token = token
        Log.d("HI", this.token)
    }

    override fun moveToQuotes() {
        val myApplication = MyApplication.instance

        token?.let { myApplication.saveToken(it) }

        //logd(id.toString())

        val action = token?.let { AuthorizationDirections.actionAuthorizationToQuotes(it) }

        action?.let { view?.let { it1 -> Navigation.findNavController(it1).navigate(it) } }
    }

    override fun showSnack(text: String){
        view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }
    }
}