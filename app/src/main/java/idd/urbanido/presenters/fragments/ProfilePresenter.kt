package idd.urbanido.presenters.fragments

import android.content.Context
import idd.urbanido.interfaces.fragments.ProfileMVP

class ProfilePresenter(private var pv: ProfileMVP.View,
                       private var pr: ProfileMVP.Repository): ProfileMVP.Presenter {
    override fun updateProfile(name: String, email: String, password: String, phone: String) {
        pv.showProfile(name, email, password, phone)
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
}