package idd.urbanido.presenters.fragments

import android.content.Context
import android.widget.EditText
import idd.urbanido.interfaces.fragments.ProfileMVP
import idd.urbanido.model.profile.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.util.logd

class ProfilePresenter(private var pv: ProfileMVP.View,
                       private var pr: ProfileMVP.Repository): ProfileMVP.Presenter {
    override fun updateProfile(name: String, email: String, password: String, phone: String) {
        pv.showProfile(name, email, password, phone)
    }

    override fun setProfile(context: Context, token: String, name: EditText, email: EditText, phone: EditText) {
        sendProfile(context, token, Profile(name.text.toString(),
                                            email.text.toString(),
                                            phone.text.toString()))
    }

    override fun getData(context: Context, token: String) {
        pr.getProfileObservable(context, token)?.subscribe ({ response->
            updateProfile(response.name, response.email,
                       response.password, response.phone)
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

    override fun sendProfile(context: Context, token: String, profile: Profile) {
        pr.updateProfileCall(context, token, profile)?.enqueue(object: Callback<Profile> {
            override fun onResponse(call: Call<Profile>,
                                    response: Response<Profile>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        val rep: String = response.body().toString()
                        logd("Response is: $rep")

                        pv.showSnack("Вы успешно обновили профиль")
                        pv.moveToQuotes()
                    }else{
                        logd("Returned empty response")
                        pv.showSnack("При авторизации произошла ошибка")
                    }
                } else {
                    pv.showSnack("Пустые поля/При авторизации произошла ошибка")
                }
            }
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                pv.showSnack("Проверьте соединение к интернету")
            }
        })
    }
}