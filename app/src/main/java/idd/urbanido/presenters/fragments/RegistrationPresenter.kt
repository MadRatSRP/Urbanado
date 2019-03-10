package idd.urbanido.presenters.fragments

import android.content.Context
import android.widget.EditText
import idd.urbanido.interfaces.fragments.RegistrationMVP
import idd.urbanido.model.RegistrationResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.util.logd

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
        ar.registerUserCall(context, registrationResponse)?.enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>,
                                    response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        val token: String = response.body().toString()
                        logd("Ответ сервера: $token")

                        av.showSnack("Вы были успешно зарегистрированы")
                        av.moveToAuthorization()
                    }else{
                        logd("Произошла ошибка: был возвращен пустой запрос")
                        av.showSnack("При авторизации произошла ошибка")
                    }
                } else {
                    av.showSnack("Пустые поля/При авторизации произошла ошибка")
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                av.showSnack("Проверьте соединение к интернету")
            }
        })
    }
}