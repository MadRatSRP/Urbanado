package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.AuthorizationMVP
import idd.urbanido.model.Auth
import idd.urbanido.model.AuthorizationResponse
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import retrofit2.Call

class AuthorizationRepository: AuthorizationMVP.Repository {
    override fun authorizeUserCall(context: Context, authorizationResponse: AuthorizationResponse)
            : Call<Auth>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.authorizeUser(authorizationResponse)
    }
}