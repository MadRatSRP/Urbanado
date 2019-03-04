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

        fun showUser(name: String, email: String, password: String, phone: String)
    }

    interface Presenter {
        fun updateUsers(users: List<RegisteredUsers>)

        fun getUsersList(context: Context)
    }

    interface Repository {
        fun getUsersObservable(context: Context): Observable<List<RegisteredUsers>>?

        fun getUserObservable(context: Context): Observable<RegisteredUsers>?
    }
}