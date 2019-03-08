package idd.urbanido.presenters.fragments

import android.content.Context
import android.util.Log
import android.widget.EditText
import idd.urbanido.interfaces.fragments.AuthorizationMVP
import idd.urbanido.model.AuthorizationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthorizationPresenter(private var av: AuthorizationMVP.View,
                             private var ar: AuthorizationMVP.Repository): AuthorizationMVP.Presenter {
    override fun authorizeUser(context: Context, login: EditText, password: EditText) {
        getData(context, AuthorizationResponse(AuthorizationResponse.User(login.text.toString(),
                password.text.toString())))
    }

    override fun getData(context: Context, authorizationResponse: AuthorizationResponse) {
        ar.authorizeUserCall(context, authorizationResponse)?.enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("HOHOHAHA", response.body().toString())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {}
        })
    }
}