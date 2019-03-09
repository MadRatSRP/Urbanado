package idd.urbanido.interfaces.fragments

import android.content.Context
import android.widget.EditText
import android.widget.ImageButton
import idd.urbanido.model.profile.Profile
import idd.urbanido.model.profile.ProfileResponse
import io.reactivex.Observable
import retrofit2.Call

interface ProfileMVP {
    interface View {
        fun setupMVP()
        fun showProfile(name: String, email: String, password: String, phone: String)

        fun setFieldEditable(editFieldValue: EditText, editFieldChangeStatus: ImageButton)
        fun setFieldNonEditable(editFieldValue: EditText, editFieldChangeStatus: ImageButton)

        fun showSnack(text: String)
        fun moveToQuotes()
    }

    interface Presenter {
        fun updateProfile(name: String, email: String, password: String, phone: String)
        fun setProfile(context: Context, token: String,
                       name: EditText, email: EditText, phone: EditText)

        fun getData(context: Context, token: String)
        fun sendProfile(context: Context, token: String, profile: Profile)
    }

    interface Repository {
        fun getProfileObservable(context: Context, token: String)
                : Observable<ProfileResponse>?
        fun updateProfileCall(context: Context, token: String, profile: Profile)
                : Call<Profile>?
    }
}