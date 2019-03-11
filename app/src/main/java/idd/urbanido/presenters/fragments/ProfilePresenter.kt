package idd.urbanido.presenters.fragments

import android.content.Context
import android.widget.EditText
import idd.urbanido.interfaces.fragments.ProfileMVP
import idd.urbanido.model.profile.ProfileResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.util.logd

class ProfilePresenter(private var pv: ProfileMVP.View,
                       private var pr: ProfileMVP.Repository): ProfileMVP.Presenter {
    override fun updateProfile(name: String, email: String, phone: String) {
        pv.showProfile(name, email, phone)
    }

    override fun setProfile(context: Context, id: String,
                            token: String, name: EditText, email: EditText, phone: EditText) {
        sendProfile(context, token,
                    id, ProfileResponse(name.text.toString(),
                                        email.text.toString(), phone.text.toString()))
    }

    override fun getData(context: Context, id: String, token: String) {
        pr.getProfileObservable(context, id, token)?.subscribe ({ response->
            updateProfile(response.name, response.email, response.phone)
            //logd("Запрос успешно получен")
            //sv.showSnack("Успешно получены данные")

            /*if (response) {
                updateShares(response.shares)
                logd("Запрос успешно получен")
                sv.showSnack("Успешно получены данные")
            } else sv.showSnack("Произошла ошибка при получении запроса")*/
        }, { error ->
            error.printStackTrace()
            //sv.showSnack("Проверьте подключение к интернету.")
        })
    }

    override fun sendProfile(context: Context, id: String,
                             token: String, profileResponse: ProfileResponse) {
        pr.updateProfileCall(context, token, id, profileResponse)?.enqueue(object: Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>,
                                    response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        val rep: String = response.body().toString()
                        logd("Response is: $rep")

                        pv.showSnack("Изменения успешно сохранены")
                    }else{
                        logd("Returned empty response")
                        pv.showSnack("При авторизации произошла ошибка")
                    }
                } else {
                    pv.showSnack("Пустые поля/При авторизации произошла ошибка")
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                pv.showSnack("Проверьте соединение к интернету")
            }
        })
    }
}