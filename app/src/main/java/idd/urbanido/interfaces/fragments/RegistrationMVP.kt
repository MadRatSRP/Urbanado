package idd.urbanido.interfaces.fragments

import android.content.Context
import android.widget.EditText
import idd.urbanido.model.RegistrationResponse
import retrofit2.Call

interface RegistrationMVP {
    interface View {
        fun setupMVP()
    }

    interface Presenter {
        fun registerUser(context: Context, registrationEmail: EditText, registrationName: EditText,
                         registrationPassword: EditText, registrationPasswordConfirm: EditText,
                         registrationPhone: EditText)
        fun getData(context: Context, registrationResponse: RegistrationResponse)
    }

    interface Repository {
        fun registerUserCall(context: Context, registrationResponse: RegistrationResponse)
                : Call<RegistrationResponse>?
    }
}