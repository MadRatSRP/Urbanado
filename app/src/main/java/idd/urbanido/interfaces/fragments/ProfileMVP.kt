package idd.urbanido.interfaces.fragments

import android.content.Context
import android.widget.EditText
import android.widget.ImageButton
import idd.urbanido.model.ProfileResponse
import io.reactivex.Observable

interface ProfileMVP {
    interface View {
        fun setupMVP()
        fun showProfile(name: String, email: String, password: String, phone: String)

        fun setFieldEditable(editFieldValue: EditText, editFieldChangeStatus: ImageButton)
        fun setFieldNonEditable(editFieldValue: EditText, editFieldChangeStatus: ImageButton)
    }

    interface Presenter {
        fun updateProfile(name: String, email: String, password: String, phone: String)
        fun getData(context: Context, token: String)
    }

    interface Repository {
        fun getProfileObservable(context: Context, token: String): Observable<ProfileResponse>?
    }
}