package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.UsersMVP
import idd.urbanido.model.registered_user.RegisteredUsers
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UsersRepository: UsersMVP.Repository {
    override fun getUsersObservable(context: Context): Observable<List<RegisteredUsers>>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getusers()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }
    override fun getUserObservable(context: Context): Observable<RegisteredUsers>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getuser("1")
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }
}