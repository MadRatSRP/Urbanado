package idd.urbanido.presenters.fragments

import android.content.Context
import idd.urbanido.interfaces.fragments.UsersMVP
import idd.urbanido.model.Users
import idd.urbanido.model.registered_user.RegisteredUsers

class UsersPresenter(private var sv: UsersMVP.View,
                     private var sr: UsersMVP.Repository) : UsersMVP.Presenter {

    override fun updateUsers(users: List<RegisteredUsers>) {
        sv.showUsers(users)
    }

    override fun getData(context: Context) {
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