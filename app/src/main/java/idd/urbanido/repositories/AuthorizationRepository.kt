package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.AuthorizationMVP
import idd.urbanido.model.IdResponse
import idd.urbanido.model.authorization.AuthorizationResponse
import idd.urbanido.model.authorization.Authorization
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

class AuthorizationRepository: AuthorizationMVP.Repository {
    override fun authorizeUserCall(context: Context, authorization: Authorization)
            : Call<AuthorizationResponse>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.authorizeUser(authorization)
    }
}