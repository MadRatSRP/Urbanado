package idd.urbanido.presenters.fragments

import android.content.Context
import android.util.Log
import android.widget.EditText
import idd.urbanido.interfaces.fragments.AuthorizationMVP
import idd.urbanido.model.Auth
import idd.urbanido.model.AuthorizationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthorizationPresenter(private var av: AuthorizationMVP.View,
                             private var ar: AuthorizationMVP.Repository): AuthorizationMVP.Presenter {

    override fun authorizeUser(context: Context, email: EditText, password: EditText) {
        getData(context, AuthorizationResponse(AuthorizationResponse.Auth(email.text.toString(),
                password.text.toString())))
    }

    override fun getData(context: Context, authorizationResponse: AuthorizationResponse) {

        ar.authorizeUserCall(context, authorizationResponse)?.enqueue(object: Callback<Auth>{
            override fun onResponse(call: Call<Auth>,
                                    response: Response<Auth>) {
                Log.i("Response", response.body().toString())

                if (response.isSuccessful){
                    if (response.body() != null){
                        Log.i("onSuccess", response.body().toString())

                        val token: String = response.body().toString()

                        av.saveToken(token.substring(9, token.length - 1))

                        av.showSnack("Вы были успешно авторизованы")

                        av.moveToQuotes()
                    }else{
                        Log.i("onEmptyResponse", "Returned empty response")
                        av.showSnack("При авторизации произошла ошибка")
                    }
                } else {
                    av.showSnack("Пустые поля/При авторизации произошла ошибка")
                }
            }
            override fun onFailure(call: Call<Auth>, t: Throwable) {
                av.showSnack("Проверьте соединение к интернету")
            }
        })
    }
}