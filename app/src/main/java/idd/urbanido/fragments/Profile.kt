package idd.urbanido.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import idd.urbanido.interfaces.fragments.ProfileMVP
import idd.urbanido.presenters.fragments.ProfilePresenter
import idd.urbanido.repositories.ProfileRepository
import idd.urbanido.util.MyApplication
import ui.util.logd
import idd.urbanido.R
import kotlinx.android.synthetic.main.fragment_profile.*


class Profile : Fragment(), ProfileMVP.View {

    private var profilePresenter: ProfilePresenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        val myApplication = MyApplication.instance
        val token = myApplication.releaseToken()
        logd("Токен пользователя получен: $token")
        val id = myApplication.releaseId().toString()
        logd("ID пользователя получен: $id")

        context?.let { token?.let { it1 ->
            profilePresenter?.getData(it, id, it1) } }

        var editNameBool = true
        var editEmailBool = true
        var editPhoneBool = true

        userNameEditValue.setOnClickListener {
            editNameBool = if (editNameBool) {
                setFieldEditable(userNameValue, userNameEditValue)
                false
            } else {
                setFieldNonEditable(userNameValue, userNameEditValue)
                true
            }
        }
        userEmailEditValue.setOnClickListener {
            editEmailBool = if (editEmailBool) {
                setFieldEditable(userEmailValue, userEmailEditValue)
                false
            } else {
                setFieldNonEditable(userEmailValue, userEmailEditValue)
                true
            }
        }
        userPhoneEditValue.setOnClickListener {
            editPhoneBool = if (editPhoneBool) {
                setFieldEditable(userPhoneValue, userPhoneEditValue)
                false
            } else {
                setFieldNonEditable(userPhoneValue, userPhoneEditValue)
                true
            }
        }

        updateProfileData.setOnClickListener {
            context?.let { it1 ->
                token?.let { it2 ->
                    profilePresenter?.setProfile(it1, id, it2,
                                                 userNameValue, userEmailValue, userPhoneValue) } }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun setupMVP() {
        profilePresenter = ProfilePresenter(this, ProfileRepository())
    }
    override fun showProfile(name: String, email: String, phone: String) {
        userNameValue.setText(name)
        userEmailValue.setText(email)
        userPhoneValue.setText(phone)
    }
    override fun setFieldEditable(editFieldValue: EditText, editFieldChangeStatus: ImageButton) {
        editFieldValue.isFocusable = true
        editFieldValue.isFocusableInTouchMode = true
        editFieldValue.isClickable = true
        editFieldChangeStatus.setImageResource(R.drawable.ic_save)
    }
    override fun setFieldNonEditable(editFieldValue: EditText, editFieldChangeStatus: ImageButton) {
        editFieldValue.isFocusable = false
        editFieldValue.isFocusableInTouchMode = false
        editFieldValue.isClickable = false
        editFieldChangeStatus.setImageResource(R.drawable.ic_edit)
    }

    override fun showSnack(text: String){
        view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }
    }

    override fun moveToQuotes() {
        view?.let { Navigation.findNavController(it).navigate(R.id.quotes) }
    }
}
