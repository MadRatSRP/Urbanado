package idd.urbanido.presenters.fragments

import android.content.Context
import idd.urbanido.interfaces.fragments.UsersMVP
import idd.urbanido.model.registered_user.RegisteredUsers

class UsersPresenter(private var sv: UsersMVP.View,
                     private var sr: UsersMVP.Repository) : UsersMVP.Presenter {

    override fun updateUsers(users: List<RegisteredUsers>) {
        sv.showUsers(users)
    }

    override fun updateUser(name: String, email: String, password: String, phone: String) {
        sv.showUser(name, email, password, phone)
    }

    override fun getUserInformation(context: Context) {
        sr.getUserObservable(context)?.subscribe ({ response->
            updateUser(response.name, response.email, response.password, response.phone)
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

    override fun getUsersList(context: Context) {
        sr.getUsersObservable(context)?.subscribe ({ response->
            updateUsers(response)
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