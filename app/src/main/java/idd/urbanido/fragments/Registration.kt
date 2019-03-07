package idd.urbanido.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import idd.urbanido.R
import idd.urbanido.model.registration.RegistrationResponse
import idd.urbanido.network.APIService
import idd.urbanido.network.ApiUtils
import kotlinx.android.synthetic.main.fragment_registration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.util.snack

class Registration : Fragment() {

    var apiService: APIService? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        apiService = ApiUtils.apiService

        registrationShowUsersList.setOnClickListener { v->
            Navigation.findNavController(v).navigate(R.id.action_registration_to_users)
        }

        registrationRegisterUser.setOnClickListener {

            /*var register = Users(registrationName.text.toString(),
                    registrationEmail.text.toString(),
                    registrationPassword.text.toString(),
                    registrationPasswordConfirm.text.toString(),
                    registrationPhone.text.toString())*/

            //var registrationResponse = RegistrationResponse(register)

            apiService?.registerUser(RegistrationResponse(RegistrationResponse.User(registrationName.text.toString(),
                    registrationEmail.text.toString(),
                    registrationPassword.text.toString(),
                    registrationPasswordConfirm.text.toString(),
                    registrationPhone.text.toString())))?.enqueue(object: Callback<RegistrationResponse>{
                override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                    //logd("post submitted to API." + response.body())
                    if (response.isSuccessful) {

                        //logd("post registration to API" + response.body().toString())
                        snack("Форма была успешно отправлена")
                    }
                    else snack("Произошла ошибка")
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    t.printStackTrace()
                    snack("Произошла ошибка")
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_registration, container, false)
    }


}
