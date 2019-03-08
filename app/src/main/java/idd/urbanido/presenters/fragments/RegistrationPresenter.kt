package idd.urbanido.presenters.fragments

import android.content.Context
import android.widget.EditText
import idd.urbanido.interfaces.fragments.RegistrationMVP
import idd.urbanido.model.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationPresenter(private var av: RegistrationMVP.View,
                             private var ar: RegistrationMVP.Repository): RegistrationMVP.Presenter {
    override fun registerUser(context: Context, registrationEmail: EditText, registrationName: EditText,
                     registrationPassword: EditText, registrationPasswordConfirm: EditText,
                     registrationPhone: EditText) {

        getData(context, RegistrationResponse(RegistrationResponse.User(registrationEmail.text.toString(),
                registrationName.text.toString(), registrationPassword.text.toString(),
                registrationPasswordConfirm.text.toString(), registrationPhone.text.toString()
        )))
    }

    override fun getData(context: Context, registrationResponse: RegistrationResponse) {
        ar.registerUserCall(context, registrationResponse)?.enqueue(object: Callback<RegistrationResponse>{
            override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {}

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {}
        })
    }
}