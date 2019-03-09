package idd.urbanido.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import idd.urbanido.R
import idd.urbanido.interfaces.fragments.ProfileMVP
import idd.urbanido.presenters.fragments.ProfilePresenter
import idd.urbanido.repositories.ProfileRepository
import idd.urbanido.util.MyApplication
import kotlinx.android.synthetic.main.fragment_profile.*
import ui.util.logd

class Profile : Fragment(), ProfileMVP.View {

    private var profilePresenter: ProfilePresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        var myApplication = MyApplication.instance

        var string = myApplication.releaseToken()

        token.text = string

        context?.let { string?.let { it1 -> profilePresenter?.getData(it, it1) } }

        /*updateProfileData.setOnClickListener {

        }*/
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
