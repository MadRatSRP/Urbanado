package idd.urbanido.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import idd.urbanido.R
import idd.urbanido.interfaces.fragments.RegistrationMVP
import idd.urbanido.presenters.fragments.RegistrationPresenter
import idd.urbanido.repositories.RegistrationRepository
import kotlinx.android.synthetic.main.fragment_registration.*

class Registration : Fragment(), RegistrationMVP.View {

    private var registrationPresenter: RegistrationPresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        registrationRegisterUser.setOnClickListener {
            context?.let {
                registrationPresenter?.registerUser(it, registrationEmail, registrationName,
                        registrationPassword, registrationPasswordConfirm,
                        registrationPhone)

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun setupMVP() {
        registrationPresenter = RegistrationPresenter(this, RegistrationRepository())
    }

    override fun moveToAuthorization() {
        view?.let { Navigation.findNavController(it).navigate(R.id.authorization) }
    }

    override fun showSnack(text: String){
        view?.let { Snackbar.make(it, text, Snackbar.LENGTH_LONG).show() }
    }
}
