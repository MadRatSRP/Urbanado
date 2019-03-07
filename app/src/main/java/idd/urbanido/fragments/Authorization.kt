package idd.urbanido.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import idd.urbanido.R
import idd.urbanido.interfaces.fragments.AuthorizationMVP
import idd.urbanido.presenters.fragments.AuthorizationPresenter
import idd.urbanido.repositories.AuthorizationRepository
import kotlinx.android.synthetic.main.fragment_authorization.*

class Authorization: Fragment(), AuthorizationMVP.View {

    private var authorizationPresenter: AuthorizationPresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        authorizeUser.setOnClickListener {
            context?.let { authorizationPresenter?.authorizeUser(it, login, password) }
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
}