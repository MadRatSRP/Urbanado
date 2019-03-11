package idd.urbanido.interfaces.fragments

import android.content.Context
import android.widget.EditText
import android.widget.ImageButton
import idd.urbanido.model.profile.ProfileResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call

interface ProfileMVP {
    interface View {
        fun setupMVP()
        fun showProfile(name: String, email: String, phone: String)

        fun setFieldEditable(editFieldValue: EditText, editFieldChangeStatus: ImageButton)
        fun setFieldNonEditable(editFieldValue: EditText, editFieldChangeStatus: ImageButton)

        fun showSnack(text: String)
        fun moveToQuotes()
    }

    interface Presenter {
        fun updateProfile(name: String, email: String, phone: String)
        fun setProfile(context: Context, id: String, token: String,
                       name: EditText, email: EditText, phone: EditText)

        fun getData(context: Context, id: String, token: String)
        fun sendProfile(context: Context, id: String,
                        token: String, profileResponse: ProfileResponse)
    }

    interface Repository {
        fun getProfileObservable(context: Context, id: String, token: String)
                : Observable<ProfileResponse>?
        fun updateProfileCall(context: Context, id: String,
                              token: String, profileResponse: ProfileResponse)
                : Call<ResponseBody>?
    }
}