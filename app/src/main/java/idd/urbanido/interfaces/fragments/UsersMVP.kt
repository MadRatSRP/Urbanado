package idd.urbanido.interfaces.fragments

import android.content.Context
import idd.urbanido.model.Users
import idd.urbanido.model.registered_user.RegisteredUsers
import idd.urbanido.model.users.UsersResponse
import io.reactivex.Observable

interface UsersMVP {
    interface View {
        fun setupMVP()

        fun showSnack(text: String)

        fun showUsers(users: List<RegisteredUsers>)
    }

    interface Presenter {
        fun updateUsers(users: List<RegisteredUsers>)

        fun getData(context: Context)
    }

    interface Repository {
        fun getUsersObservable(context: Context): Observable<List<RegisteredUsers>>?
    }
}