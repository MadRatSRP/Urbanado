package idd.urbanido.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import idd.urbanido.R
import idd.urbanido.interfaces.fragments.ProfileMVP
import idd.urbanido.presenters.fragments.ProfilePresenter
import idd.urbanido.repositories.ProfileRepository
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class Profile : Fragment(), ProfileMVP.View {

    private var profilePresenter: ProfilePresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        context?.let { profilePresenter?.getData(it) }

        updateProfileData.setOnClickListener {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        //bundle.putString("token", token)
        //bundle.putBundle("authBundle", bundle)

        return view
    }

    override fun setupMVP() {
        profilePresenter = ProfilePresenter(this, ProfileRepository())
    }

    override fun showProfile(name: String, email: String, password: String, phone: String) {
        userNameValue.text = name
        userEmailValue.text = email
        userPasswordValue.text = password
        userPhoneValue.text = phone
    }
}
