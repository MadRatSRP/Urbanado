package idd.urbanido.presenters.fragments

import android.content.Context
import android.widget.EditText
import idd.urbanido.interfaces.fragments.AuthorizationMVP
import idd.urbanido.model.authorization.AuthorizationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthorizationPresenter(private var av: AuthorizationMVP.View,
                             private var ar: AuthorizationMVP.Repository): AuthorizationMVP.Presenter {
    override fun authorizeUser(context: Context, login: EditText, password: EditText) {
        getData(context,
                AuthorizationResponse(AuthorizationResponse.User(login.text.toString(),
                                                                 password.text.toString())))
    }

    override fun getData(context: Context, authorizationResponse: AuthorizationResponse) {
        /*sr.getSharesObservable(context)?.subscribe ({ response->
            if (response.success) {
                updateShares(response.shares)
                logd("Запрос успешно получен")
                sv.showSnack("Успешно получены данные")
            } else sv.showSnack("Произошла ошибка при получении запроса")
        }, { error ->
            error.printStackTrace()
            sv.showSnack("Проверьте подключение к интернету.")
        })*/
        ar.authorizeUserCall(context, authorizationResponse)?.enqueue(object: Callback<AuthorizationResponse>{
            override fun onResponse(call: Call<AuthorizationResponse>, response: Response<AuthorizationResponse>) {
            }

            override fun onFailure(call: Call<AuthorizationResponse>, t: Throwable) {
            }
        })
    }
}